package com.example.two.service;

import com.example.two.entity.Example;
import com.example.two.entity.Task;
import com.example.two.payload.ApiResult;
import com.example.two.payload.ExampleDto;
import com.example.two.repository.ExampleRepository;
import com.example.two.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {
    @Autowired
    ExampleRepository exampleRepository;
    @Autowired
    TaskRepository taskRepository;

    public List<Example> get() {
        return exampleRepository.findAll();
    }

    public ApiResult add(ExampleDto exampleDto) {
        Optional<Task> taskRepositoryById = taskRepository.findById(exampleDto.getTask_id());
        if (taskRepositoryById.isEmpty()) {
            return new ApiResult("task not found",false);
        }
        Task task = taskRepositoryById.get();
        Example example = new Example();
        example.setTask(task);
        example.setText(exampleDto.getText());
        return new ApiResult("added",true);
    }

    public ApiResult edit(Integer id, ExampleDto example) {
        Optional<Example> byId = exampleRepository.findById(id);
        if (byId.isEmpty()) {
            return new ApiResult("example not found",false);
        }
        Optional<Task> taskRepositoryById = taskRepository.findById(example.getTask_id());
        if (taskRepositoryById.isEmpty()) {
            return new ApiResult("task not found",false);
        }
        Task task = taskRepositoryById.get();
        Example example1 = byId.get();
        example1.setText(example.getText());
        example1.setTask(task);
        return new ApiResult("successful edited",true);
    }

    public ApiResult delete(Integer id)
    {
        try {
            exampleRepository.deleteById(id);
            return new ApiResult("deleted", true);
        }
        catch (Exception e)
        {
            return new ApiResult("example object not found",false);
        }
    }


}
