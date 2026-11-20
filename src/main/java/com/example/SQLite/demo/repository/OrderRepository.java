package com.example.SQLite.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SQLite.demo.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // user side
    List<Order> findByUserId(Long userId);

    // restaurant side (all orders)
    List<Order> findByRestaurantUserId(Long restaurantUserId);

    // 🔥 incoming orders for restaurant
    List<Order> findByRestaurantUserIdAndStatus(
            Long restaurantUserId,
            String status
    );

    // optional
    List<Order> findByStatus(String status);
}
