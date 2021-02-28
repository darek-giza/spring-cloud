package com.darek.giza.userservice.repository;

import com.darek.giza.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> getAll();
    User create(User user);
    Optional<User> findById(String id);
    void deleteById(String id);
}
