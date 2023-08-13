package com.example.two.controller;

import com.example.two.entity.Answer;
import com.example.two.payload.AnswerDto;
import com.example.two.payload.ApiResult;
import com.example.two.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coding_bat.com/answer")
public class AnswerController {
    @Autowired
    AnswerService resultService;

    @GetMapping
    public ResponseEntity<List<Answer>>get()
    {
        List<Answer> result = resultService.get();
        return ResponseEntity.ok().body(result);
    }
    @PostMapping
    public ResponseEntity<ApiResult>add(@RequestBody AnswerDto answerDto)
    {
        ApiResult add = resultService.add(answerDto);
        return ResponseEntity.status(add.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(add);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResult>edit(@PathVariable Integer id,@RequestBody AnswerDto answerDto)
    {
        ApiResult edit = resultService.edit(id, answerDto);
        return ResponseEntity.status(edit.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(edit);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResult>delete(@PathVariable Integer id)
    {
        ApiResult delete = resultService.delete(id);

        return ResponseEntity.status(delete.isSuccess()?HttpStatus.NO_CONTENT:HttpStatus.NOT_FOUND).body(delete);
    }


}
