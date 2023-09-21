package com.caio.alura.api.model.meeting;

import java.time.LocalDateTime;

import com.caio.alura.api.enums.Department;

public record MeetingRegisterData(Long teacherId, Long studentId, LocalDateTime dateTime, Department department) {

    public MeetingRegisterData(MeetingUserInputData userInput) {
        this(userInput.teacherId(), userInput.studentId(), userInput.dateTime(), Department.fromValue(userInput.department()));
    }
    
}
