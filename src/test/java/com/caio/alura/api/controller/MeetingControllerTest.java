package com.caio.alura.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.caio.alura.api.model.meeting.MeetingRegisterReturnData;
import com.caio.alura.api.model.meeting.MeetingUserInputData;
import com.caio.alura.api.service.MeetingService;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class MeetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<MeetingUserInputData> meetingUserInputData;

    @Autowired
    private JacksonTester<MeetingRegisterReturnData> meetingRegisterReturnData;

    @MockBean
    private MeetingService meetingService;

    @Test
    @WithMockUser
    void status400WhenInputIsInvalid() throws Exception {
        var response = this.mockMvc.perform(MockMvcRequestBuilders.post("/meeting"))
                .andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    @WithMockUser
    void status200WhenInputIsValid() throws Exception {
        var dateTime = LocalDateTime.now().plusHours(1);
        var department = "biologia";
        var userInput = meetingUserInputData.write(
                new MeetingUserInputData(1l, 2l, dateTime, department)).getJson();
        var returnData = new MeetingRegisterReturnData(null, 1l, 2l, dateTime);
        
        when(meetingService.schedule(any())).thenReturn(returnData);

        var response = this.mockMvc.perform(MockMvcRequestBuilders.post("/meeting")
                .contentType(MediaType.APPLICATION_JSON).content(userInput))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        var expectedResponseContent = meetingRegisterReturnData.write(returnData).getJson();

        assertEquals(expectedResponseContent, response.getContentAsString());
    }

}
