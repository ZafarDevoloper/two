package com.example.two.service;

import com.example.two.entity.Answer;
import com.example.two.entity.Task;
import com.example.two.entity.User;
import com.example.two.payload.AnswerDto;
import com.example.two.payload.ApiResult;
import com.example.two.repository.AnswererRepository;
import com.example.two.repository.TaskRepository;
import com.example.two.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    AnswererRepository answererRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TaskRepository taskRepository;

    public List<Answer> get() {
        return answererRepository.findAll();
    }

    public ApiResult add(AnswerDto answerDto) {
        Optional<User> userRepositoryById = userRepository.findById(answerDto.getUser_id());
        Optional<Task> taskRepositoryById = taskRepository.findById(answerDto.getTask_id());
        if (userRepositoryById.isEmpty() || taskRepositoryById.isEmpty())
            return new ApiResult("user or task not found", false);
        Answer answer = new Answer();
        answer.set_correct(answerDto.is_correct());
        answer.setText(answerDto.getText());
        answer.setUser(userRepositoryById.get());
        answer.setTask(taskRepositoryById.get());

        answererRepository.save(answer);
        return new ApiResult("added", true);
    }

    public ApiResult edit(Integer id, AnswerDto answerDto) {
        Optional<Answer> byId = answererRepository.findById(id);
        if (byId.isEmpty()) {
            return new ApiResult("not found",false);
        }
        Optional<User> userRepositoryById = userRepository.findById(answerDto.getUser_id());
        Optional<Task> taskRepositoryById = taskRepository.findById(answerDto.getTask_id());
        if (userRepositoryById.isEmpty() || taskRepositoryById.isEmpty())
            return new ApiResult("user or task not found", false);
        Answer answer = byId.get();
        answer.set_correct(answerDto.is_correct());
        answer.setText(answerDto.getText());
        User user = userRepositoryById.get();
        Task task = taskRepositoryById.get();
        answer.setUser(user);
        answer.setTask(task);
        answererRepository.save(answer);
        return new ApiResult("edited",true);
    }

    public ApiResult delete(Integer id) {
        try {
            answererRepository.deleteById(id);
            return new ApiResult("this data delete", true);
        } catch (Exception e) {
            return new ApiResult("un_success", false);
        }
    }


}
