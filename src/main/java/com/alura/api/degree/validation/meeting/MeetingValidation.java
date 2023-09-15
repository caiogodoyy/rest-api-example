package com.alura.api.degree.validation.meeting;

import org.springframework.stereotype.Component;

import com.alura.api.degree.model.meeting.ScheduleMeetingData;

@Component
public interface MeetingValidation {
    
    void validate(ScheduleMeetingData data);

}
