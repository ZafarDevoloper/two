package com.example.two.controller;

import com.example.two.entity.Category;
import com.example.two.payload.ApiResult;
import com.example.two.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coding_bat.com/category")
public class CategoryControler {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>>get()
    {
        List<Category> categories = categoryService.get();
        return ResponseEntity.ok().body(categories);
    }
    @PostMapping
    public ResponseEntity<ApiResult>add(@RequestBody Category category)
    {
        ApiResult add = categoryService.add(category);
        return ResponseEntity.status(add.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(add);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResult>edit(@PathVariable Integer id,@RequestBody Category category )
    {
        ApiResult edit = categoryService.edit(id, category);
        return ResponseEntity.status(edit.isSuccess()?202:405).body(edit);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResult>delete(@PathVariable Integer id)
    {
        ApiResult delete = categoryService.delete(id);
        return ResponseEntity.status(delete.isSuccess()?HttpStatus.NO_CONTENT:HttpStatus.METHOD_NOT_ALLOWED).body(delete);
    }


}
