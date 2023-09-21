package com.caio.alura.api.validation.meeting;

import org.springframework.stereotype.Component;

import com.caio.alura.api.model.meeting.MeetingRegisterData;

@Component
public interface MeetingValidation {
    
    void validate(MeetingRegisterData data);

}
