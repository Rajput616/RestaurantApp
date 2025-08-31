package com.delhifoodhouse.restaurant.controller;

import com.delhifoodhouse.restaurant.entity.Dish;
import com.delhifoodhouse.restaurant.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/") // ✅ base path
public class ViewController {

    @Autowired
    private DishService dishService;

    // ✅ Home - list all dishes
    @GetMapping
    public String home(Model model) {
        model.addAttribute("dishes", dishService.getAllDishes());
        model.addAttribute("dish", new Dish()); // Empty object for Add form
        return "index"; // renders index.html
    }

    // ✅ Order Dish (increment count)
    @PostMapping("/order/{id}")
    public String orderDish(@PathVariable Long id) {
        dishService.orderDish(id);
        return "redirect:/";
    }

    // ✅ Add Dish
    @PostMapping("/add")
    public String addDish(@ModelAttribute Dish dish) {
        dishService.addDish(dish);
        return "redirect:/";
    }

    // ✅ Edit Dish (load into form)
    @GetMapping("/edit/{id}")
    public String editDish(@PathVariable Long id, Model model) {
        Dish dish = dishService.getDishById(id); // ✅ use service instead of stream filtering
        model.addAttribute("dish", dish);
        model.addAttribute("dishes", dishService.getAllDishes());
        return "index"; // reuse index.html
    }

    // ✅ Update Dish
    @PostMapping("/update/{id}")
    public String updateDish(@PathVariable Long id, @ModelAttribute Dish dish) {
        dishService.updateDish(id, dish);
        return "redirect:/";
    }

    // ✅ Delete Dish
    @GetMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return "redirect:/";
    }
}
