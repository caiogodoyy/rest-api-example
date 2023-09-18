package com.alura.api.degree.validation.meeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alura.api.degree.model.meeting.ScheduleMeetingData;
import com.alura.api.degree.service.StudentService;

import jakarta.validation.ValidationException;

@Component
public class ActiveStudentValidation implements MeetingValidation {

    @Autowired
    StudentService studentService;
    
    @Override
    public void validate(ScheduleMeetingData data) {
        if (!studentService.isActive(data.studentId())) {
            throw new ValidationException("Student is not active");
        }
    }
    
}
