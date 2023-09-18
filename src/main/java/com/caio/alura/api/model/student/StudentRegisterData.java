package com.caio.alura.api.model.student;

import com.caio.alura.api.model.address.AddressRegisterData;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentRegisterData(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotNull String gender,
        @NotBlank String grade,
        @NotBlank String phone,
        @NotNull @Valid AddressRegisterData address) {

}
