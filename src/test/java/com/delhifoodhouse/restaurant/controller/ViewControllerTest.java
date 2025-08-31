package com.delhifoodhouse.restaurant.controller;

import com.delhifoodhouse.restaurant.entity.Dish;
import com.delhifoodhouse.restaurant.service.DishService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // enable Mockito
class ViewControllerTest {

    @Mock
    private DishService dishService; // dependency mocked

    @InjectMocks
    private ViewController viewController; // class under test

    @Test
    void testHome_ShouldReturnIndexWithDishes() {
        Dish dish = new Dish();
        when(dishService.getAllDishes()).thenReturn(List.of(dish));

        Model model = new ConcurrentModel();
        String viewName = viewController.home(model);

        assertThat(viewName).isEqualTo("index");
        assertThat(model.getAttribute("dishes")).isEqualTo(List.of(dish));
        assertThat(model.getAttribute("dish")).isInstanceOf(Dish.class);
    }

    @Test
    void testOrderDish_ShouldRedirectToHome() {
        String viewName = viewController.orderDish(1L);

        verify(dishService, times(1)).orderDish(1L);
        assertThat(viewName).isEqualTo("redirect:/");
    }

    @Test
    void testAddDish_ShouldRedirectToHome() {
        Dish dish = new Dish();

        String viewName = viewController.addDish(dish);

        verify(dishService).addDish(dish);
        assertThat(viewName).isEqualTo("redirect:/");
    }

    @Test
    void testEditDish_ShouldReturnIndex() {
        Dish dish = new Dish();
        when(dishService.getDishById(1L)).thenReturn(dish);
        when(dishService.getAllDishes()).thenReturn(List.of(dish));

        Model model = new ConcurrentModel();
        String viewName = viewController.editDish(1L, model);

        assertThat(viewName).isEqualTo("index");
        assertThat(model.getAttribute("dish")).isEqualTo(dish);
        assertThat(model.getAttribute("dishes")).isEqualTo(List.of(dish));
    }

    @Test
    void testUpdateDish_ShouldRedirectToHome() {
        Dish dish = new Dish();

        String viewName = viewController.updateDish(1L, dish);

        verify(dishService).updateDish(1L, dish);
        assertThat(viewName).isEqualTo("redirect:/");
    }

    @Test
    void testDeleteDish_ShouldRedirectToHome() {
        String viewName = viewController.deleteDish(1L);

        verify(dishService).deleteDish(1L);
        assertThat(viewName).isEqualTo("redirect:/");
    }
}
