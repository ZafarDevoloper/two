package com.example.two.payload;

import lombok.Data;

@Data
public class AnswerDto {
    private String text;
    private boolean is_correct;
    private Integer user_id;
    private Integer task_id;
}
