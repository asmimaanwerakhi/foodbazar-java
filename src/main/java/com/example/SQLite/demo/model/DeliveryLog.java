package com.example.SQLite.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "delivery_logs")
public class DeliveryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long deliveryUserId;
    private Long orderId;
    private double amount;

    // ASSIGNED / COMPLETED
    private String status;

    // ===== Getter & Setter =====
    public Long getId() { return id; }

    public Long getDeliveryUserId() { return deliveryUserId; }
    public void setDeliveryUserId(Long deliveryUserId) {
        this.deliveryUserId = deliveryUserId;
    }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public double getAmount() { return amount; }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) {
        this.status = status;
    }
}
