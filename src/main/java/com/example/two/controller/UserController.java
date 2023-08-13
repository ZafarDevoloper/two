package com.example.two.controller;

import com.example.two.entity.Example;
import com.example.two.entity.User;
import com.example.two.payload.ApiResult;
import com.example.two.payload.UserDto;
import com.example.two.service.TaskService;
import com.example.two.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("/api/coding_bat.com/user")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping
    public ResponseEntity<Page<User>>get(@RequestParam int page)
    {
        Page<User> examples = service.get(page);
        return ResponseEntity.ok().body(examples);
    }
    @PostMapping
    public ResponseEntity<ApiResult>add(@Validated @RequestBody UserDto example)
    {
        ApiResult add = service.add(example);
        return ResponseEntity.status(add.isSuccess()? HttpStatus.CREATED:HttpStatus.INTERNAL_SERVER_ERROR).body(add);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResult>edit(@Validated@PathVariable Integer id,@RequestBody UserDto example)
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
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
