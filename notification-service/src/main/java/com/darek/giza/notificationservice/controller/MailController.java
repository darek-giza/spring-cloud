package com.darek.giza.notificationservice.controller;

import com.darek.giza.notificationservice.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;

@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @GetMapping(value = "/sendMail/{email}")
    public ResponseEntity<String> sendMail(@PathVariable("email")@Email String email){
        mailService.sendMail(email);
        return new ResponseEntity<>("Email Sent Successfully!!", HttpStatus.OK);
    }
}
