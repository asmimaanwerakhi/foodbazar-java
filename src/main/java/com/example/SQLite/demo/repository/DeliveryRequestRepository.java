package com.example.SQLite.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SQLite.demo.model.DeliveryRequest;

public interface DeliveryRequestRepository
        extends JpaRepository<DeliveryRequest, Long> {

    List<DeliveryRequest> findByDeliveryUserIdAndStatus(
            Long deliveryUserId, String status);

    List<DeliveryRequest> findByStatus(String status);
}