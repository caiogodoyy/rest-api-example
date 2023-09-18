package com.caio.alura.api.model.student;

import com.caio.alura.api.enums.Gender;

public record StudentRegisterReturnBody(String name, Gender gender, String grade, String state, String city) {
    
    public StudentRegisterReturnBody(Student student) {
        this(student.getName(), student.getGender(), student.getGrade(), student.getAddress().getState(), student.getAddress().getCity());
    }

}
