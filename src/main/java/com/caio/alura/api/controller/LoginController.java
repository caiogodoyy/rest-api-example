package com.caio.alura.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caio.alura.api.model.user.User;
import com.caio.alura.api.model.user.UserLoginData;
import com.caio.alura.api.model.user.UserToken;
import com.caio.alura.api.service.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<UserToken> login(@RequestBody @Valid UserLoginData data) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var userAuthentication = authenticationManager.authenticate(authenticationToken);

        var token = tokenService.createToken((User) userAuthentication.getPrincipal());

        return ResponseEntity.ok(new UserToken(token));
    }

}
