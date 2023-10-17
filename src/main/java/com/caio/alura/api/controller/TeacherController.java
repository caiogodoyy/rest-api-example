package com.caio.alura.api.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caio.alura.api.model.teacher.Teacher;
import com.caio.alura.api.model.teacher.TeacherRegisterData;
import com.caio.alura.api.model.teacher.TeacherRegisterReturnBody;
import com.caio.alura.api.model.teacher.TeacherUserInputData;
import com.caio.alura.api.service.TeacherService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/teacher")
@SecurityRequirement(name = "bearer-key")
public class TeacherController {

    private final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherService teacherService;

    @PostMapping
    @Transactional
    public ResponseEntity<TeacherRegisterReturnBody> registerTeacher(@RequestBody @Valid TeacherUserInputData userInput) {
        logger.info(".registerTeacher: Received POST request for /teacher");

        var data = new TeacherRegisterData(userInput);

        var teacher = new Teacher(data);

        this.teacherService.saveTeacher(teacher);

        var uri = URI.create("/teacher/" + teacher.getId());

        logger.info(".registerTeacher: Responded with 201 status for POST request to /teacher");
        return ResponseEntity.created(uri).body(new TeacherRegisterReturnBody(teacher));
    }

    @GetMapping
    public ResponseEntity<Page<TeacherRegisterReturnBody>> listAllTeachers(Pageable pageable) {
        logger.info(".listAllTeachers: Received GET request for /teacher");

        var page = this.teacherService.getAllActiveTeachers(pageable);

        logger.info(".listAllTeachers: Responded with 200 status for GET request to /teacher");
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherRegisterReturnBody> getTeacher(@PathVariable Long id) {
        logger.info(".getTeacher: Received GET request for /teacher/" + id);

        var teacher = this.teacherService.getTeacherById(id);

        logger.info(".getTeacher: Responded with 200 status for GET request to /teacher/" + id);
        return ResponseEntity.ok(new TeacherRegisterReturnBody(teacher));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> inactivateTeacher(@PathVariable Long id) {
        logger.info(".inactivateTeacher: Received DELETE request for /teacher/{}", id);

        this.teacherService.inactivateTeacherById(id);

        logger.info(".inactivateTeacher: Responded with 203 status for DELETE request to /teacher/" + id);
        return ResponseEntity.noContent().build();
    }

}
