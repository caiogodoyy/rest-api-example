package com.alura.api.degree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.api.degree.domain.meeting.MeetingRepository;
import com.alura.api.degree.dto.meeting.ScheduleMeetingData;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

    @Autowired
    MeetingRepository meetingRepository;
    
    @PostMapping
    @Transactional
    public ResponseEntity<ScheduleMeetingData> scheduleMeeting(@RequestBody @Valid ScheduleMeetingData data) {
        return ResponseEntity.ok(new ScheduleMeetingData(null, null, null));
    }
    
}
