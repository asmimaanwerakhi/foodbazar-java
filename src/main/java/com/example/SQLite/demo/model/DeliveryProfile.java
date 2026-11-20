package com.example.SQLite.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "delivery_profile")
public class DeliveryProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔑 users.id
    private Long userId;

    private String name;
    private String contact;

    // AVAILABLE / BUSY
    private String availability;

    // per delivery charge or %
    private double commissionRate;

    // ===== Getter & Setter =====

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
 
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAvailability() {
        return availability;
    }
    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public double getCommissionRate() {
        return commissionRate;
    }
    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }
}
