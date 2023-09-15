package com.alura.api.degree.model.meeting;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record ScheduleMeetingData(
                Long teacherId,
                @NotNull Long studentId,
                @NotNull @Future LocalDateTime dateTime) {

        public ScheduleMeetingData(Meeting meeting) {
                this(meeting.getTeacher().getId(), meeting.getStudent().getId(), meeting.getDateTime());
        }

}
