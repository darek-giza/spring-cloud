package com.darek.giza.userservice.service;

import com.darek.giza.userservice.model.User;

import java.util.List;

public interface UserService{

    User getById(String id);
    User updateById(User user);
    List<User> getAll();

}
