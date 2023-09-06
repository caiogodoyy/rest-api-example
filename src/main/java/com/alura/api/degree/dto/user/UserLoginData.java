package com.alura.api.degree.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UserLoginData(
        @NotBlank String username,
        @NotBlank String password) {

}
