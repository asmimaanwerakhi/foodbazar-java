package com.example.SQLite.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SQLite.demo.model.UserProfile;

public interface UserProfileRepository 
        extends JpaRepository<UserProfile, Long> {

    UserProfile findByUserId(Long userId);
}
