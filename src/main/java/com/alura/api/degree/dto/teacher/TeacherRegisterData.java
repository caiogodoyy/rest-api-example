package com.alura.api.degree.dto.teacher;

import com.alura.api.degree.dto.address.AddressRegisterData;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TeacherRegisterData(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotNull String gender,
        @NotBlank String department,
        @NotNull Float salary,
        @NotBlank String phone,
        @NotNull @Valid AddressRegisterData address) {

}
