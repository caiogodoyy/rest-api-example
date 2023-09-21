package com.caio.alura.api.model.teacher;

import com.caio.alura.api.enums.Department;
import com.caio.alura.api.enums.Gender;
import com.caio.alura.api.model.address.AddressRegisterData;

public record TeacherRegisterData(String name, String email, Gender gender, Department department, Double salary,
        String phone, AddressRegisterData address) {

    public TeacherRegisterData(TeacherUserInputData userInput) {
        this(userInput.name(), userInput.email(), Gender.fromValue(userInput.gender()),
                Department.fromValue(userInput.department()), userInput.salary(), userInput.phone(),
                userInput.address());
    }

}
