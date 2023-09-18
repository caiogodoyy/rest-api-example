package com.caio.alura.api.model.user;

import jakarta.validation.constraints.NotBlank;

public record UserLoginData(
        @NotBlank String username,
        @NotBlank String password) {

}
