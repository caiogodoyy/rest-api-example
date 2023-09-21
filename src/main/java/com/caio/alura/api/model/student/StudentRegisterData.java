package com.caio.alura.api.model.student;

import com.caio.alura.api.enums.Gender;
import com.caio.alura.api.model.address.AddressRegisterData;

public record StudentRegisterData(String name, String email, Gender gender, String grade, String phone,
                AddressRegisterData address) {

        public StudentRegisterData(StudentUserInputData userInput) {
                this(userInput.name(), userInput.email(), Gender.fromValue(userInput.gender()),
                                userInput.grade(), userInput.phone(), userInput.address());
        }

}
