package com.caio.alura.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caio.alura.api.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deactivateUser(@PathVariable Long id) {
        logger.info(".deactivateUser: Received DELETE request for /user/{}", id);

        this.userService.inactivateUserById(id);

        logger.info(".deactivateUser: Responded with 203 status for POST request to /user/{}", id);
        return ResponseEntity.noContent().build();
    }

}
