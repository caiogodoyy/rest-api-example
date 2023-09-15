package com.alura.api.degree.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.api.degree.model.meeting.Meeting;
import com.alura.api.degree.model.meeting.ScheduleMeetingData;
import com.alura.api.degree.service.MeetingService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

    @Autowired
    MeetingService service;
    
    @PostMapping
    @Transactional
    public ResponseEntity<ScheduleMeetingData> scheduleMeeting(@RequestBody @Valid ScheduleMeetingData data) {
        var meeting = new Meeting(data);

        var uri = URI.create("/meeting/" + meeting.getId());
        return ResponseEntity.created(uri).body(new ScheduleMeetingData(meeting));
    }
    
}
