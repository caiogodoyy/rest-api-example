package com.alura.api.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.api.med.domain.user.User;
import com.alura.api.med.domain.user.UserRepository;
import com.alura.api.med.dto.user.UserRegisterData;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<UserRegisterData> registerUser(@RequestBody @Valid UserRegisterData data,
            UriComponentsBuilder uriBuilder) {
        var user = new User(data);

        userRepository.save(user);

        var uri = uriBuilder.path("/teacher/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserRegisterData(user));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deactivateUser(@PathVariable Long id) {
        var user = userRepository.getReferenceById(id);
        user.deactivate();

        return ResponseEntity.noContent().build();
    }

}
