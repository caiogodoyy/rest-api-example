package com.caio.alura.api.controller;

import java.net.URI;

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
import com.caio.alura.api.service.TeacherService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService service;

    @PostMapping
    @Transactional
    public ResponseEntity<TeacherRegisterReturnBody> registerTeacher(@RequestBody @Valid TeacherRegisterData data) {
        var teacher = new Teacher(data);

        service.save(teacher);

        var uri = URI.create("/teacher/" + teacher.getId());
        return ResponseEntity.created(uri).body(new TeacherRegisterReturnBody(teacher));
    }

    @GetMapping
    public ResponseEntity<Page<TeacherRegisterReturnBody>> listAllTeachers(Pageable pageable) {
        var page = service.getAllActiveTeachers(pageable);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherRegisterReturnBody> getTeacher(@PathVariable Long id) {
        var teacher = service.getTeacherById(id);
        return ResponseEntity.ok(new TeacherRegisterReturnBody(teacher));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deactivateTeacher(@PathVariable Long id) {
        service.inactivateTeacherById(id);

        return ResponseEntity.noContent().build();
    }

}
