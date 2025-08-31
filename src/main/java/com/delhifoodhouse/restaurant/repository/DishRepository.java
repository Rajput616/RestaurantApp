package com.delhifoodhouse.restaurant.repository;

import com.delhifoodhouse.restaurant.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findAllByOrderByOrderCountDesc();
}
