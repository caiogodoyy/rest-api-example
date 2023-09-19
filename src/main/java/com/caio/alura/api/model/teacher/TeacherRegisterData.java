package com.caio.alura.api.model.teacher;

import com.caio.alura.api.model.address.AddressRegisterData;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TeacherRegisterData(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotNull String gender,
        @NotBlank String department,
        @NotNull Double salary,
        @NotBlank String phone,
        @NotNull @Valid AddressRegisterData address) {

}
