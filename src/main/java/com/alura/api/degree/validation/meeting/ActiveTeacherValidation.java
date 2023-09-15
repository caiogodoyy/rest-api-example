package com.alura.api.degree.validation.meeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alura.api.degree.model.meeting.ScheduleMeetingData;
import com.alura.api.degree.service.TeacherService;

import jakarta.validation.ValidationException;

@Component
public class ActiveTeacherValidation implements MeetingValidation {

    @Autowired
    TeacherService teacherService;

    public void validate(ScheduleMeetingData data) {
        if (data.teacherId() != null) {
            if (!teacherService.isActive(data.teacherId())) {
                throw new ValidationException("Teacher is not active");
            }
        }
    }
    
}
