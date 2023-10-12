package com.caio.alura.api.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caio.alura.api.model.user.User;
import com.caio.alura.api.model.user.UserRegisterData;
import com.caio.alura.api.service.UserService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<UserRegisterData> registerUser(@RequestBody @Valid UserRegisterData data) {
        logger.info(".registerUser: Received POST request for /register");

        var user = new User(data);

        this.userService.saveUser(user);

        var uri = URI.create("/user/" + user.getId());

        logger.info(".registerUser: Responded with 201 status for POST request to /register");
        return ResponseEntity.created(uri).body(new UserRegisterData(user));
    }

}
