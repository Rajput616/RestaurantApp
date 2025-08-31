package com.delhifoodhouse.restaurant.service;

import com.delhifoodhouse.restaurant.entity.Dish;
import com.delhifoodhouse.restaurant.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    // ✅ Get all dishes (sorted by order count)
    public List<Dish> getAllDishes() {
        return dishRepository.findAllByOrderByOrderCountDesc();
    }

    // ✅ Get single dish by ID
    public Dish getDishById(Long dishId) {
        return dishRepository.findById(dishId)
                .orElseThrow(() -> new RuntimeException("Dish not found with id: " + dishId));
    }

    // ✅ Increment order count (place an order)
    public Dish orderDish(Long dishId) {
        Dish dish = getDishById(dishId);
        dish.setOrderCount(dish.getOrderCount() + 1);
        return dishRepository.save(dish);
    }

    // ✅ Add new dish
    public Dish addDish(Dish dish) {
        dish.setOrderCount(0L); // new dish starts with 0 orders
        return dishRepository.save(dish);
    }

    // ✅ Update existing dish
    public Dish updateDish(Long dishId, Dish updatedDish) {
        Dish dish = getDishById(dishId);
        dish.setName(updatedDish.getName());
        dish.setPrice(updatedDish.getPrice());
        return dishRepository.save(dish);
    }

    // ✅ Delete dish
    public void deleteDish(Long dishId) {
        if (!dishRepository.existsById(dishId)) {
            throw new RuntimeException("Dish not found with id: " + dishId);
        }
        dishRepository.deleteById(dishId);
    }
}
