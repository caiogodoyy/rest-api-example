package com.alura.api.degree.controller;

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
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.api.degree.model.student.Student;
import com.alura.api.degree.model.student.StudentRegisterData;
import com.alura.api.degree.model.student.StudentRegisterReturnBody;
import com.alura.api.degree.service.StudentService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService service;

    @PostMapping
    @Transactional
    public ResponseEntity<StudentRegisterReturnBody> registerStudent(@RequestBody @Valid StudentRegisterData data,
            UriComponentsBuilder uriBuilder) {
        var student = new Student(data);

        service.save(student);

        var uri = URI.create("/student/" + student.getId());
        return ResponseEntity.created(uri).body(new StudentRegisterReturnBody(student));
    }

    @GetMapping
    public ResponseEntity<Page<StudentRegisterReturnBody>> listAllStudents(Pageable pageable) {
        var page = service.getAllActiveStudents(pageable);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentRegisterReturnBody> getStudent(@PathVariable Long id) {
        var student = service.getStudentById(id);
        return ResponseEntity.ok(new StudentRegisterReturnBody(student));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> inactivateStudent(@PathVariable Long id) {
        service.inactivateStudentById(id);

        return ResponseEntity.noContent().build();
    }

}
