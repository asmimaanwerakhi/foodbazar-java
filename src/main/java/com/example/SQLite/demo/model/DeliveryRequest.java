package com.example.SQLite.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "delivery_requests")
public class DeliveryRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private Long restaurantUserId;
    private Long deliveryUserId;

    private double deliveryCharge;

    // PENDING / ACCEPTED
    private String status;

    // ===== Getter & Setter =====

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getRestaurantUserId() {
        return restaurantUserId;
    }
    public void setRestaurantUserId(Long restaurantUserId) {
        this.restaurantUserId = restaurantUserId;
    }

    public Long getDeliveryUserId() {
        return deliveryUserId;
    }
    public void setDeliveryUserId(Long deliveryUserId) {
        this.deliveryUserId = deliveryUserId;
    }

    public double getDeliveryCharge() {
        return deliveryCharge;
    }
    public void setDeliveryCharge(double deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
