package com.darek.giza.userservice.service;

import com.darek.giza.userservice.model.user.User;
import com.darek.giza.userservice.model.user.dto.UserRequest;
import com.darek.giza.userservice.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepositoryImpl userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }


    @Override
    public User getById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateById(String id, UserRequest userRequest) {
        User user = userRepository.takById(id);
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        return userRepository.update(user);
    }

    @Override
    public User create(User user) {
        user.setCreatedAt(new Date());
        return userRepository.create(user);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
