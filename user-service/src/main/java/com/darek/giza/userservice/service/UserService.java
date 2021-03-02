package com.darek.giza.userservice.service;

import com.darek.giza.userservice.model.User;
import com.darek.giza.userservice.model.UserPartial;

import java.util.List;

public interface UserService {

    User getById(String id);

    User updateById(String id, UserPartial userPartial);

    User create(User user);

    List<User> getAll();

    void deleteById(String id);

}
