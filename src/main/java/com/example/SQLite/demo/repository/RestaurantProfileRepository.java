package com.example.SQLite.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SQLite.demo.model.RestaurantProfile;

public interface RestaurantProfileRepository
        extends JpaRepository<RestaurantProfile, Long> {

    RestaurantProfile findByUserId(Long userId);
}
