package com.example.two.repository;

import com.example.two.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByEmail(String email);
}
