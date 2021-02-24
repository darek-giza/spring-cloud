package com.darek.giza.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping(path = "/hello")
    public String seyHello(){
        return "sey heeello from Dario :) ";
    }
}
