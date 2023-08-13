package com.example.two.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    private String text;
    private String solution;
    private String hint;
    @Column(nullable = false)
    private String method;
    private boolean has_star=false;
    @OneToMany
    private List<Language> language;

}
