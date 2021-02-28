package com.darek.giza.userservice.service;

import com.darek.giza.userservice.model.User;
import com.darek.giza.userservice.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepositoryImpl dal;

    @Override
    public List<User> getAll() {
        return dal.getAll();
    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public User updateById(User user) {
        return dal.create(user);
    }
}
