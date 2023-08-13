package com.example.two.service;

import com.example.two.entity.Category;
import com.example.two.payload.ApiResult;
import com.example.two.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public List<Category>get()
    {
        return categoryRepository.findAll();
    }

    public ApiResult add(Category category)
    {
categoryRepository.save(category);
return new ApiResult("added",true);
    }
    public ApiResult edit(Integer id, Category category)
    {
        Optional<Category> categoryRepositoryById = categoryRepository.findById(id);
        if (categoryRepositoryById.isEmpty()) {
            return new ApiResult("not found",false);
        }
        Category category1 = categoryRepositoryById.get();
        category1.setDescription(category.getDescription());
        category1.setName(category.getName());
        categoryRepository.save(category1);
        return new ApiResult("edit",true);
    }
    public ApiResult delete(Integer id)
    {
        try {
            categoryRepository.deleteById(id);
            return new ApiResult("successful deleted",true);
        }
        catch (Exception e)
        {
            return new ApiResult("unsuccessful deleted",false);
        }

    }


}
