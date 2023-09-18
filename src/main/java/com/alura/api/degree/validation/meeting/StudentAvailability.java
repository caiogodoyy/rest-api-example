package com.alura.api.degree.validation.meeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alura.api.degree.model.meeting.ScheduleMeetingData;
import com.alura.api.degree.repository.MeetingRepository;

import jakarta.validation.ValidationException;

@Component
public class StudentAvailability implements MeetingValidation {

    @Autowired
    MeetingRepository meetingRepository;

    @Override
    public void validate(ScheduleMeetingData data) {
        var begin = data.dateTime().withHour(7);
        var end = data.dateTime().withHour(18);

        var studentHasMeetingInTheSameDay = meetingRepository.existsByStudentIdAndDateTimeBetween(data.studentId(), begin, end);

        if (studentHasMeetingInTheSameDay) {
            throw new ValidationException("Student already has a meeting at that day");
        }
    }
    
}
