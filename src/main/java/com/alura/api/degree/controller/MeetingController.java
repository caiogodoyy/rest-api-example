package com.alura.api.degree.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.api.degree.model.meeting.ScheduleMeetingData;
import com.alura.api.degree.model.meeting.ScheduleMeetingReturnData;
import com.alura.api.degree.service.MeetingService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

    @Autowired
    MeetingService meetingService;
    
    @PostMapping
    @Transactional
    public ResponseEntity<ScheduleMeetingReturnData> scheduleMeeting(@RequestBody @Valid ScheduleMeetingData data) {
        var meeting = meetingService.schedule(data);

        var uri = URI.create("/meeting/" + meeting.getId());
        return ResponseEntity.created(uri).body(new ScheduleMeetingReturnData(meeting));
    }
    
}
