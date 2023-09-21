package com.caio.alura.api.validation.meeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.caio.alura.api.model.meeting.MeetingRegisterData;
import com.caio.alura.api.service.StudentService;

import jakarta.validation.ValidationException;

@Component
public class ActiveStudentValidation implements MeetingValidation {

    @Autowired
    private StudentService studentService;
    
    @Override
    public void validate(MeetingRegisterData data) {
        if (!this.studentService.isActive(data.studentId())) {
            throw new ValidationException("Student is not active");
        }
    }
    
}
