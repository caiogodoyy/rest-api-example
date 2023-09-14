package com.alura.api.degree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.api.degree.service.UserService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deactivateUser(@PathVariable Long id) {
        service.inactivateUserById(id);

        return ResponseEntity.noContent().build();
    }
    
}
