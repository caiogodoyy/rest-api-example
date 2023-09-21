package com.caio.alura.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caio.alura.api.model.meeting.MeetingUserInputData;
import com.caio.alura.api.model.meeting.MeetingRegisterData;
import com.caio.alura.api.model.meeting.MeetingRegisterReturnData;
import com.caio.alura.api.service.MeetingService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/meeting")
@SecurityRequirement(name = "bearer-key")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;
    
    @PostMapping
    @Transactional
    public ResponseEntity<MeetingRegisterReturnData> scheduleMeeting(@RequestBody @Valid MeetingUserInputData userInput) {
        var data = new MeetingRegisterData(userInput);

        var returnData = this.meetingService.schedule(data);

        return ResponseEntity.ok(returnData);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> cancelMeeting(@PathVariable Long id) {
        this.meetingService.cancel(id);

        return ResponseEntity.noContent().build();
    }
    
}
