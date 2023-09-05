package com.alura.api.med.dto.user;

import com.alura.api.med.domain.user.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegisterData(
    @NotBlank String username, 
    @NotBlank String password, 
    @NotBlank @Email String email, 
    @NotBlank String name) {
        public UserRegisterData(User user) {
            this(user.getUsername(), user.getPassword(), user.getEmail(), user.getName());
        }
}
