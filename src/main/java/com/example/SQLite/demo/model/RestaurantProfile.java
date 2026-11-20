package com.example.SQLite.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurant_profile")
public class RestaurantProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // users.id
    private Long userId;

    private String restaurantName;
    private String ownerName;
    private String tradeId;
    private String address;

    // ACTIVE / BLOCKED
    private String status = "ACTIVE";

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getTradeId() {
        return tradeId;
    }
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
