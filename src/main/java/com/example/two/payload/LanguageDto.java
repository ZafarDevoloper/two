package com.example.two.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class LanguageDto {
    @NotNull(message = "Language name must not be null")
    private String name;
    private List<Integer> category_id;

}
