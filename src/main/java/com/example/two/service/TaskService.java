package com.example.two.service;
import com.example.two.entity.Language;
import com.example.two.entity.Task;
import com.example.two.payload.ApiResult;
import com.example.two.payload.TaskDto;
import com.example.two.repository.LanguageRepository;
import com.example.two.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    LanguageRepository languageRepository;

    public List<Task> get() {
        return taskRepository.findAll();
    }

    public ApiResult add(TaskDto taskDto) {
        List<Language> languages = new ArrayList<>();
        List<Integer> categoryId = taskDto.getLanguage_ids();
        for (Integer integer : categoryId) {
            Optional<Language> categoryRepositoryById = languageRepository.findById(integer);
            categoryRepositoryById.ifPresent(languages::add);
        }
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setSolution(taskDto.getSolution());
        task.setText(taskDto.getText());
        task.setHas_star(taskDto.isHas_star());
        task.setHint(taskDto.getHint());
        task.setLanguage(languages);
        return new ApiResult("data saved",true);
    }

    public ApiResult edit(Integer id, TaskDto taskDto)
    {
        List<Language> languages = new ArrayList<>();
        List<Integer> categoryId = taskDto.getLanguage_ids();
        for (Integer integer : categoryId) {
            Optional<Language> categoryRepositoryById = languageRepository.findById(integer);
            categoryRepositoryById.ifPresent(languages::add);
        }
        Optional<Task> taskRepositoryById = taskRepository.findById(id);
        if (taskRepositoryById.isPresent()) {
            Task task = taskRepositoryById.get();
            task.setLanguage(languages);
            task.setName(taskDto.getName());
            task.setSolution(taskDto.getSolution());
            task.setText(taskDto.getText());
            task.setHas_star(taskDto.isHas_star());
            task.setHint(taskDto.getHint());
            return new ApiResult("edited",true);
        }
        return new ApiResult("not found",true);

    }

    public ApiResult delete(Integer id) {
try{
    taskRepository.deleteById(id);
    return new ApiResult("deleted",true);
}
catch (Exception e)
{
    return new ApiResult("unsuccessful deleted",false);
}
    }


}
