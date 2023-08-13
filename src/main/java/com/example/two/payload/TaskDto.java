package com.example.two.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class TaskDto {
    private String name;
    private String text;
    private String solution;
    private String hint;
    @NotNull(message = "method null bulmasligi keerak")
    private String method;
    private boolean has_star;
    private List<Integer>language_ids;
}
