package com.example.two.payload;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {
    @NotNull(message = "email null bulmasligi kerak")
    private String email;
    @NotNull(message = "password null bulmasligi kerak")
    private String password;
}
