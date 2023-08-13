package com.example.two.repository;

import com.example.two.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<Example,Integer> {
}
