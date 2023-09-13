package com.alura.api.degree.model.student;

import com.alura.api.degree.enums.Gender;

public record StudentRegisterReturnBody(String name, Gender gender, String grade, String state, String city) {
    
    public StudentRegisterReturnBody(Student student) {
        this(student.getName(), student.getGender(), student.getGrade(), student.getAddress().getState(), student.getAddress().getCity());
    }

}
