package com.example.two.controller;

import com.example.two.entity.Example;
import com.example.two.entity.Language;
import com.example.two.payload.ApiResult;
import com.example.two.payload.LanguageDto;
import com.example.two.service.ExampleService;
import com.example.two.service.LanguageService;
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

import static com.example.two.controller.TaskController.getStringStringMap;

@RestController
@RequestMapping("/api/coding_bat.com/language")
public class LanguageController {
    @Autowired
    LanguageService exampleService;

    @GetMapping
    public ResponseEntity<List<Language>>get()
    {
        List<Language> examples = exampleService.get();
        return ResponseEntity.ok().body(examples);
    }
    @PostMapping
    public ResponseEntity<ApiResult>add(@Validated @RequestBody LanguageDto language)
    {
        ApiResult add = exampleService.add(language);
        return ResponseEntity.status(add.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(add);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResult>edit(@Validated @PathVariable Integer id,@RequestBody LanguageDto example)
    {
        ApiResult edit = exampleService.edit(id, example);
        return ResponseEntity.status(edit.isSuccess()?202:405).body(edit);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResult>delete(@PathVariable Integer id)
    {
        ApiResult delete = exampleService.delete(id);
        return ResponseEntity.status(delete.isSuccess()?HttpStatus.NO_CONTENT:HttpStatus.METHOD_NOT_ALLOWED).body(delete);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        return getStringStringMap(ex);
    }
}
