package com.alura.api.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.api.med.dto.user.UserLoginData;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginData data) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var userAuthentication = authenticationManager.authenticate(authenticationToken);

        return ResponseEntity.ok().build();
    }

}
