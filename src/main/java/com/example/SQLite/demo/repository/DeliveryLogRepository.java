package com.example.SQLite.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SQLite.demo.model.DeliveryLog;

public interface DeliveryLogRepository
        extends JpaRepository<DeliveryLog, Long> {

    List<DeliveryLog> findByDeliveryUserId(Long deliveryUserId);

    List<DeliveryLog> findByDeliveryUserIdAndStatus(
            Long deliveryUserId, String status);
}
