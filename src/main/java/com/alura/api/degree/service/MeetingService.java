package com.alura.api.degree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.api.degree.model.meeting.Meeting;
import com.alura.api.degree.model.meeting.MeetingRepository;

@Service
public class MeetingService {
    
    @Autowired
    MeetingRepository repository;

    public void save(Meeting meeting) {
        repository.save(meeting);
    }

}
