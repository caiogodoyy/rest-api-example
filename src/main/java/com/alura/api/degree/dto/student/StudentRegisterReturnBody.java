package com.alura.api.degree.dto.student;

import com.alura.api.degree.domain.student.Student;
import com.alura.api.degree.dto.Gender;

public record StudentRegisterReturnBody(String name, Gender gender, String grade, String state, String city) {
    
    public StudentRegisterReturnBody(Student student) {
        this(student.getName(), student.getGender(), student.getGrade(), student.getAddress().getState(), student.getAddress().getCity());
    }

}
