package com.example.two.service;

import com.example.two.entity.User;
import com.example.two.payload.ApiResult;
import com.example.two.payload.UserDto;
import com.example.two.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Page<User> get(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return userRepository.findAll(pageable);
    }

    public ApiResult add(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            return new ApiResult("bunday user mavjud",false);
        }
        User user = new User();
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
        return new ApiResult("added", true);
    }

    public ApiResult edit(Integer id, UserDto user) {
        Optional<User> userRepositoryById = userRepository.findById(id);
        if (userRepositoryById.isPresent()) {
            User user1 = userRepositoryById.get();
            user1.setEmail(user.getEmail());
            user1.setPassword(user.getPassword());
            userRepository.save(user1);
            return new ApiResult("edit", true);
        }
        return new ApiResult("not found", false);
    }

    public ApiResult delete(Integer id) {
        try {
            userRepository.deleteById(id);
            return new ApiResult("deleted", true);
        } catch (Exception e) {
            return new ApiResult("unsuccessful deleted", false);
        }
    }


}
