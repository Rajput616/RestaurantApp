package com.delhifoodhouse.restaurant.controller;

import com.delhifoodhouse.restaurant.entity.Dish;
import com.delhifoodhouse.restaurant.service.DishService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DishController.class)
class DishControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean   // ✅ correct annotation
    private DishService dishService;

    @Test
    void testGetAllDishes() throws Exception {
        when(dishService.getAllDishes()).thenReturn(
                Arrays.asList(
                        new Dish(1L, "Pizza", 10L, 250.0),
                        new Dish(2L, "Burger", 5L, 150.0)
                )
        );

        mockMvc.perform(get("/api/dishes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Pizza"))
                .andExpect(jsonPath("$[0].orderCount").value(10))
                .andExpect(jsonPath("$[0].price").value(250.0))
                .andExpect(jsonPath("$[1].name").value("Burger"))
                .andExpect(jsonPath("$[1].orderCount").value(5))
                .andExpect(jsonPath("$[1].price").value(150.0));
    }

    @Test
    void testOrderDish() throws Exception {
        when(dishService.orderDish(1L)).thenReturn(
                new Dish(1L, "Pizza", 9L, 250.0)
        );

        mockMvc.perform(post("/api/dishes/1/order")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pizza"))
                .andExpect(jsonPath("$.orderCount").value(9))
                .andExpect(jsonPath("$.price").value(250.0));
    }
}
