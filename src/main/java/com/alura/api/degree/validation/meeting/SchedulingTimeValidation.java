package com.alura.api.degree.validation.meeting;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.alura.api.degree.model.meeting.ScheduleMeetingData;

import jakarta.validation.ValidationException;

@Component
public class SchedulingTimeValidation implements MeetingValidation {

    public void validate(ScheduleMeetingData data) {
        var meetingDate = data.dateTime();
        var currentDate = LocalDateTime.now();

        var timeDifference = Duration.between(currentDate, meetingDate).toMinutes();

        if (timeDifference < 30) {
            throw new ValidationException("Meeting must be scheduled 30 minutes in advance");
        }
    }

}
