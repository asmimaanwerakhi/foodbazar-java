package com.example.SQLite.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SQLite.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
