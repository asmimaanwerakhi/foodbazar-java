package com.example.SQLite.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SQLite.demo.model.FoodPost;

public interface FoodPostRepository
        extends JpaRepository<FoodPost, Long> {

    List<FoodPost> findByRestaurantUserId(Long restaurantUserId);
}
