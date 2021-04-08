package com.darek.giza.userservice.service;

import com.darek.giza.userservice.model.user.User;
import com.darek.giza.userservice.model.user.dto.UserRequest;

import java.util.List;

public interface UserService {

    User getById(String id);

    User updateById(String id, UserRequest userRequest);

    User create(User user);

    List<User> getAll();

    void deleteById(String id);

}
