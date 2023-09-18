package com.caio.alura.api.controller;

import java.net.URI;

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

    @Autowired
    UserService service;

    @PostMapping
    @Transactional
    public ResponseEntity<UserRegisterData> registerUser(@RequestBody @Valid UserRegisterData data) {
        var user = new User(data);

        service.save(user);

        var uri = URI.create("/user/" + user.getId());
        return ResponseEntity.created(uri).body(new UserRegisterData(user));
    }

}
