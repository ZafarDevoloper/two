package com.example.two.service;

import com.example.two.entity.Category;
import com.example.two.entity.Language;
import com.example.two.payload.ApiResult;
import com.example.two.payload.LanguageDto;
import com.example.two.repository.CategoryRepository;
import com.example.two.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<Language> get() {
        return languageRepository.findAll();
    }

    public ApiResult add(LanguageDto languageDto) {
        List<Category> categories = new ArrayList<>();
        List<Integer> categoryId = languageDto.getCategory_id();
        for (Integer integer : categoryId) {
            Optional<Category> categoryRepositoryById = categoryRepository.findById(integer);
            categoryRepositoryById.ifPresent(categories::add);
        }
        Language language = new Language();
        language.setName(languageDto.getName());
        language.setCategory(categories);
        languageRepository.save(language);
        return new ApiResult("added",true);
    }

    public ApiResult edit(Integer id, LanguageDto languageDto) {
        List<Category> categories = new ArrayList<>();
        List<Integer> categoryId = languageDto.getCategory_id();
        for (Integer integer : categoryId) {
            Optional<Category> categoryRepositoryById = categoryRepository.findById(integer);
            categoryRepositoryById.ifPresent(categories::add);
        }
        Optional<Language> byId = languageRepository.findById(id);
        if (byId.isPresent()) {
            Language language = byId.get();
            language.setName(languageDto.getName());
            language.setCategory(categories);
        }
        return new ApiResult("unsuccessful",false);
    }

    public ApiResult delete(Integer id) {
        try {
            languageRepository.deleteById(id);
            return new ApiResult("deleted",true);
        }
        catch (Exception e)
        {
            return new ApiResult("unsuccessful deleted",false);
        }

    }


}
