package com.caio.alura.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caio.alura.api.model.meeting.ScheduleMeetingData;
import com.caio.alura.api.model.meeting.ScheduleMeetingReturnData;
import com.caio.alura.api.service.MeetingService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/meeting")
@SecurityRequirement(name = "bearer-key")
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
