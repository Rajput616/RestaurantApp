package com.delhifoodhouse.restaurant.controller;

import org.springframework.ui.Model;
import com.delhifoodhouse.restaurant.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewController {
    @Autowired
    private DishService dishService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("dishes", dishService.getAllDishes());
        return "index";
    }

    @PostMapping("/order/{id}")
    public String orderDish(@PathVariable Long id) {
        dishService.orderDish(id);
        return "redirect:/";
    }
}

