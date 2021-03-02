package com.darek.giza.userservice.controller;

import com.darek.giza.userservice.model.User;
import com.darek.giza.userservice.model.UserPartial;
import com.darek.giza.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/user")
    public List<User> getAll() {
        return userService.getAll();
    }


    @GetMapping(value = "/user/{id}")
    public User getById(@PathVariable("id") String id) {
        return userService.getById(id);
    }

    @PostMapping(value = "/user")
    public void create(@RequestBody User user) {
        userService.create(user);
    }

    @PutMapping(value = "user/{id}")
    public void update(@PathVariable("id") String id,
                       @RequestBody UserPartial userPartial) {
        userService.updateById(id, userPartial);
    }

    @DeleteMapping(value = "user/{id}")
    public void deleteById(@PathVariable("id") String id) {
        userService.deleteById(id);
    }
}
