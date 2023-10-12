package com.caio.alura.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<UserToken> login(@RequestBody @Valid UserLoginData data) {
        logger.info(".login: Received POST request for /login");

        var authenticationToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var userAuthentication = this.authenticationManager.authenticate(authenticationToken);

        var token = this.tokenService.createToken((User) userAuthentication.getPrincipal());

        logger.info(".login: Responded with 200 status for POST request to /login");
        return ResponseEntity.ok(new UserToken(token));
    }

}
