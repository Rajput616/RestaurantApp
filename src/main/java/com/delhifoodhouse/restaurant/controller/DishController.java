package com.delhifoodhouse.restaurant.controller;

import com.delhifoodhouse.restaurant.entity.Dish;
import com.delhifoodhouse.restaurant.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
public class DishController {
    @Autowired
    private DishService dishService;

    @GetMapping
    public List<Dish> getDishes() {
        return dishService.getAllDishes();
    }

    @PostMapping("/{id}/order")
    public Dish orderDish(@PathVariable Long id) {
        return dishService.orderDish(id);
    }
}

