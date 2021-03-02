package com.darek.giza.userservice.repository;

import com.darek.giza.userservice.model.AppException;
import com.darek.giza.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> getAll();

    User create(User user);

    User findById(String id);

    default User takById(String id) {
        return Optional.ofNullable(findById(id)).orElseThrow(
            () -> AppException.notFound("User with ID: " + id + " doesn't exist"));
    }

    User update(User user);

    void deleteById(String id);
}
