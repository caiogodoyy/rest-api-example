package com.alura.api.med.dto.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressRegisterData(
        @NotBlank @Pattern(regexp = "\\d{8}") String postalCode,
        String state, String city, String street) {

}
