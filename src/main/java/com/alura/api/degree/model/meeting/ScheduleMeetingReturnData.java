package com.alura.api.degree.model.meeting;

import java.time.LocalDateTime;

public record ScheduleMeetingReturnData(Long id, Long teacherId, Long studentId, LocalDateTime dateTime) {

    public ScheduleMeetingReturnData(Meeting meeting) {
        this(meeting.getId(), meeting.getTeacher().getId(), meeting.getStudent().getId(), meeting.getDateTime());
    }
    
}
