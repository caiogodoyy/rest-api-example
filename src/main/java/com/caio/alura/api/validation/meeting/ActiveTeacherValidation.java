package com.caio.alura.api.validation.meeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.caio.alura.api.model.meeting.MeetingRegisterData;
import com.caio.alura.api.service.TeacherService;

import jakarta.validation.ValidationException;

@Component
public class ActiveTeacherValidation implements MeetingValidation {

    @Autowired
    private TeacherService teacherService;

    @Override
    public void validate(MeetingRegisterData data) {
        if (data.teacherId() != null && !this.teacherService.isActive(data.teacherId())) {
            throw new ValidationException("Teacher is not active");
        }
    }
    
}
