package com.caio.alura.api.validation.meeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.caio.alura.api.model.meeting.MeetingRegisterData;
import com.caio.alura.api.repository.MeetingRepository;

import jakarta.validation.ValidationException;

@Component
public class StudentAvailability implements MeetingValidation {

    @Autowired
    private MeetingRepository meetingRepository;

    @Override
    public void validate(MeetingRegisterData data) {
        var begin = data.dateTime().withHour(7);
        var end = data.dateTime().withHour(18);

        var studentHasMeetingInTheSameDay = this.meetingRepository.existsByStudentIdAndDateTimeBetween(data.studentId(), begin, end);

        if (studentHasMeetingInTheSameDay) {
            throw new ValidationException("Student already has a meeting at that day");
        }
    }
    
}
