
package com.example.SQLite.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SQLite.demo.model.DeliveryProfile;

public interface DeliveryProfileRepository
        extends JpaRepository<DeliveryProfile, Long> {

    DeliveryProfile findByUserId(Long userId);

    List<DeliveryProfile> findByAvailability(String availability);
}