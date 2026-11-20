package com.example.SQLite.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "food_post")
public class FoodPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // which restaurant posted
    private Long restaurantUserId;

    private String foodName;
    private double price;

    @Column(length = 1000)
    private String description;

    private String imageName;

    // getters & setters
    public Long getId() {
        return id;
    }

    public Long getRestaurantUserId() {
        return restaurantUserId;
    }
    public void setRestaurantUserId(Long restaurantUserId) {
        this.restaurantUserId = restaurantUserId;
    }

    public String getFoodName() {
        return foodName;
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
