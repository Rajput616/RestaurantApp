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

    public List<Dish> getAllDishes() {
        return dishRepository.findAllByOrderByOrderCountDesc();
    }

    public Dish orderDish(Long dishId) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new RuntimeException("Dish not found"));
        dish.setOrderCount(dish.getOrderCount() + 1);
        return dishRepository.save(dish);
    }
}

