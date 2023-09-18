package com.alura.api.degree.validation.meeting;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import com.alura.api.degree.model.meeting.ScheduleMeetingData;

import jakarta.validation.ValidationException;

@Component
public class OpeningTimeValidation implements MeetingValidation {
    
    @Override
    public void validate(ScheduleMeetingData data) {
        var meetingDate = data.dateTime();

        var isWeekend = meetingDate.getDayOfWeek() == DayOfWeek.SATURDAY || meetingDate.getDayOfWeek() == DayOfWeek.SUNDAY;
        var isBefore7 = meetingDate.getHour() < 7;
        var isAfter18 = meetingDate.getHour() > 18;

        if (isWeekend || isBefore7 || isAfter18) {
            throw new ValidationException("Meeting outside opening hours");
        }
    }
    
}
