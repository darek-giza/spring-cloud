package com.darek.giza.userservice.controller;

import com.darek.giza.userservice.model.User;
import com.darek.giza.userservice.model.UserPartial;
import com.darek.giza.userservice.service.UserService;
import com.netflix.discovery.EurekaClient;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Lazy
    private EurekaClient client;

    @GetMapping(value = "/user")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping(value = "/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getById(@PathVariable("id") String id) {
        return userService.getById(id);
    }

    @PostMapping(value = "/user")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody User user) {
        String email = userService.create(user).getEmail();
        if (email != null){
            sendMail(email);
            return "User created successful !!!";
        }
        return "Sorry, something went wrong";
    }

    @PutMapping(value = "user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") String id,
                       @RequestBody UserPartial userPartial) {
        userService.updateById(id, userPartial);
    }

    @DeleteMapping(value = "user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") String id) {
        userService.deleteById(id);
    }

    public void sendMail(String email){
        try {
            String ipAddr = client.getNextServerFromEureka("notification-service", false).getIPAddr();
            String url = "http://" + ipAddr + ":8086/sendMail/" + email;
            restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
