package com.alura.api.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.api.med.domain.teacher.Teacher;
import com.alura.api.med.domain.teacher.TeacherRepository;
import com.alura.api.med.dto.teacher.TeacherRegisterData;
import com.alura.api.med.dto.teacher.TeacherRegisterReturnBody;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherRepository teacherRepository;
    
    @PostMapping
    @Transactional
    public ResponseEntity<TeacherRegisterReturnBody> registerTeacher(@RequestBody @Valid TeacherRegisterData data, UriComponentsBuilder uriBuilder) {
        var teacher = new Teacher(data);
        
        teacherRepository.save(teacher);

        var uri = uriBuilder.path("/teacher/{id}").buildAndExpand(teacher.getId()).toUri();
        return ResponseEntity.created(uri).body(new TeacherRegisterReturnBody(teacher));
    }

}
