package com.alura.api.degree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.api.degree.model.user.User;
import com.alura.api.degree.model.user.UserRegisterData;
import com.alura.api.degree.repository.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<UserRegisterData> registerUser(@RequestBody @Valid UserRegisterData data,
            UriComponentsBuilder uriBuilder) {
        var user = new User(data);

        userRepository.save(user);

        var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserRegisterData(user));
    }

}
