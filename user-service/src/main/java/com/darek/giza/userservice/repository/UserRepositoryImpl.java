package com.darek.giza.userservice.repository;

import com.darek.giza.userservice.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public User deleteById(String id) {
        return null;
    }
}
