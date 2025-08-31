package com.delhifoodhouse.restaurant.service;

import com.delhifoodhouse.restaurant.entity.Dish;
import com.delhifoodhouse.restaurant.repository.DishRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DishServiceTest {

    @Mock
    private DishRepository dishRepository;

    @InjectMocks
    private DishService dishService;

    @Test
    void testGetAllDishes() {
        List<Dish> dishes = Arrays.asList(
                new Dish(1L, "Pizza", 10L, 250.0),
                new Dish(2L, "Burger", 5L, 150.0)
        );
        when(dishRepository.findAllByOrderByOrderCountDesc()).thenReturn(dishes);

        List<Dish> result = dishService.getAllDishes();

        assertEquals(2, result.size());
        assertEquals("Pizza", result.get(0).getName());
        verify(dishRepository, times(1)).findAllByOrderByOrderCountDesc();
    }

    @Test
    void testGetDishById_Found() {
        Dish dish = new Dish(1L, "Pizza", 10L, 250.0);
        when(dishRepository.findById(1L)).thenReturn(Optional.of(dish));

        Dish result = dishService.getDishById(1L);

        assertNotNull(result);
        assertEquals("Pizza", result.getName());
    }

    @Test
    void testGetDishById_NotFound() {
        when(dishRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> dishService.getDishById(99L));

        assertEquals("Dish not found with id: 99", exception.getMessage());
    }

    @Test
    void testOrderDish() {
        Dish dish = new Dish(1L, "Pizza", 10L, 250.0);
        when(dishRepository.findById(1L)).thenReturn(Optional.of(dish));
        when(dishRepository.save(any(Dish.class))).thenAnswer(i -> i.getArguments()[0]);

        Dish updatedDish = dishService.orderDish(1L);

        assertEquals(11, updatedDish.getOrderCount());
        verify(dishRepository, times(1)).save(dish);
    }

    @Test
    void testAddDish() {
        Dish newDish = new Dish(null, "Pasta", 0L, 200.0);
        when(dishRepository.save(any(Dish.class))).thenReturn(new Dish(1L, "Pasta", 0L, 200.0));

        Dish savedDish = dishService.addDish(newDish);

        assertNotNull(savedDish);
        assertEquals("Pasta", savedDish.getName());
        verify(dishRepository, times(1)).save(newDish);
    }

    @Test
    void testUpdateDish() {
        Dish existingDish = new Dish(1L, "Pizza", 5L, 250.0);
        Dish updatedDish = new Dish(1L, "Burger", 5L, 300.0);

        when(dishRepository.findById(1L)).thenReturn(Optional.of(existingDish));
        when(dishRepository.save(any(Dish.class))).thenAnswer(i -> i.getArguments()[0]);

        Dish result = dishService.updateDish(1L, updatedDish);

        assertEquals("Burger", result.getName());
        assertEquals(300.0, result.getPrice());
        verify(dishRepository, times(1)).save(existingDish);
    }

    @Test
    void testDeleteDish_Success() {
        when(dishRepository.existsById(1L)).thenReturn(true);

        dishService.deleteDish(1L);

        verify(dishRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteDish_NotFound() {
        when(dishRepository.existsById(99L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> dishService.deleteDish(99L));

        assertEquals("Dish not found with id: 99", exception.getMessage());
    }
}
