package com.darek.giza.userservice.service;

import com.darek.giza.userservice.model.User;
import com.darek.giza.userservice.model.UserPartial;
import com.darek.giza.userservice.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public User updateById(String id, UserPartial userPartial) {
        User user = userRepository.takById(id);
        user.setFirstName(userPartial.getFirstName());
        user.setLastName(userPartial.getLastName());
        return userRepository.update(user);
    }

    @Override
    public User create(User user) {
        return userRepository.create(user);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
