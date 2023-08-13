package com.example.two.repository;

import com.example.two.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language,Integer> {
}
