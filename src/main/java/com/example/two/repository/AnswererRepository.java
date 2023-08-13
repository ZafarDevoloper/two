package com.example.two.repository;

import com.example.two.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswererRepository extends JpaRepository<Answer,Integer> {
}
