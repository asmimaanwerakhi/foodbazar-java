package com.example.SQLite.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // who ordered
    private Long userId;

    // which restaurant
    private Long restaurantUserId;

    // which food
    private Long foodId;

    private int quantity;
    private double totalPrice;

    private String deliveryAddress;

    // PLACED / ACCEPTED / DELIVERED
    private String status;

    // ================= GETTERS & SETTERS =================

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRestaurantUserId() {
        return restaurantUserId;
    }
    public void setRestaurantUserId(Long restaurantUserId) {
        this.restaurantUserId = restaurantUserId;
    }

    public Long getFoodId() {
        return foodId;
    }
    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
