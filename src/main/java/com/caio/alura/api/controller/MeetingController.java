package com.caio.alura.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(MeetingController.class);

    @Autowired
    private MeetingService meetingService;
    
    @PostMapping
    @Transactional
    public ResponseEntity<MeetingRegisterReturnData> scheduleMeeting(@RequestBody @Valid MeetingUserInputData userInput) {
        logger.info(".scheduleMeeting: Received POST request for /meeting");

        var data = new MeetingRegisterData(userInput);

        var returnData = this.meetingService.schedule(data);

        logger.info(".scheduleMeeting: Responded with 201 status for POST request to /meeting");

        return ResponseEntity.ok(returnData);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> cancelMeeting(@PathVariable Long id) {
        logger.info(".cancelMeeting: Received DELETE request for /meeting/" + id);

        this.meetingService.cancel(id);

        logger.info(".cancelMeeting: Responded with 203 status for DELETE request to /meeting");
        return ResponseEntity.noContent().build();
    }
    
}
