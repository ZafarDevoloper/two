package com.example.two.controller;

import com.example.two.entity.Category;
import com.example.two.entity.Example;
import com.example.two.payload.ApiResult;
import com.example.two.payload.ExampleDto;
import com.example.two.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coding_bat.com/example")
public class ExampleController {
    @Autowired
    ExampleService exampleService;

    @GetMapping
    public ResponseEntity<List<Example>>get()
    {
        List<Example> examples = exampleService.get();
        return ResponseEntity.ok().body(examples);
    }
    @PostMapping
    public ResponseEntity<ApiResult>add(@RequestBody ExampleDto example)
    {
        ApiResult add = exampleService.add(example);
        return ResponseEntity.status(add.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(add);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResult>edit(@PathVariable Integer id,@RequestBody ExampleDto example)
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


}
