package com.example.two.controller;

import com.example.two.entity.Example;
import com.example.two.entity.Task;
import com.example.two.payload.ApiResult;
import com.example.two.payload.TaskDto;
import com.example.two.service.ExampleService;
import com.example.two.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/coding_bat.com/task")
public class TaskController {
    @Autowired
    TaskService service;

    @GetMapping
    public ResponseEntity<List<Task>>get()
    {
        List<Task> examples = service.get();
        return ResponseEntity.ok().body(examples);
    }
    @PostMapping
    public ResponseEntity<ApiResult>add(@Validated@RequestBody TaskDto example)
    {
        ApiResult add = service.add(example);
        return ResponseEntity.status(add.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(add);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResult>edit(@Validated @PathVariable Integer id, @RequestBody TaskDto example)
    {
        ApiResult edit = service.edit(id, example);
        return ResponseEntity.status(edit.isSuccess()?202:405).body(edit);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResult>delete(@PathVariable Integer id)
    {
        ApiResult delete = service.delete(id);
        return ResponseEntity.status(delete.isSuccess()?HttpStatus.NO_CONTENT:HttpStatus.METHOD_NOT_ALLOWED).body(delete);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        return getStringStringMap(ex);
    }
    static Map<String, String> getStringStringMap(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
