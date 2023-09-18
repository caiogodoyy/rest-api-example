package com.caio.alura.api.validation.meeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.caio.alura.api.model.meeting.ScheduleMeetingData;
import com.caio.alura.api.service.TeacherService;

import jakarta.validation.ValidationException;

@Component
public class ActiveTeacherValidation implements MeetingValidation {

    @Autowired
    TeacherService teacherService;

    @Override
    public void validate(ScheduleMeetingData data) {
        if (data.teacherId() != null && !teacherService.isActive(data.teacherId())) {
            throw new ValidationException("Teacher is not active");
        }
    }
    
}
