package com.caio.alura.api.validation.meeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.caio.alura.api.model.meeting.MeetingRegisterData;
import com.caio.alura.api.repository.MeetingRepository;

import jakarta.validation.ValidationException;

@Component
public class TeacherAvailabilityValidation implements MeetingValidation {

    @Autowired
    private MeetingRepository meetingRepository;

    @Override
    public void validate(MeetingRegisterData data) {
        if (data.teacherId() != null) {
            var teacherHasMeetingInTheSameTime = this.meetingRepository.existsByTeacherIdAndDateTime(data.teacherId(), data.dateTime());
            if (teacherHasMeetingInTheSameTime) {
                throw new ValidationException("Teacher already has a meeting at that time");
            }
        }
    }

}
