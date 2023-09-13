package com.alura.api.degree.model.teacher;

import com.alura.api.degree.enums.Gender;

public record TeacherRegisterReturnBody(String name, Gender gender, String department, String state, String city) {
    
    public TeacherRegisterReturnBody(Teacher teacher) {
        this(teacher.getName(), teacher.getGender(), teacher.getDepartment(), teacher.getAddress().getState(), teacher.getAddress().getCity());
    }

}
