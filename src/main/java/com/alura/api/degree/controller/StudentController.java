package com.alura.api.degree.controller;

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

import com.alura.api.degree.domain.student.Student;
import com.alura.api.degree.domain.student.StudentRepository;
import com.alura.api.degree.dto.student.StudentRegisterData;
import com.alura.api.degree.dto.student.StudentRegisterReturnBody;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<StudentRegisterReturnBody> registerStudent(@RequestBody @Valid StudentRegisterData data,
            UriComponentsBuilder uriBuilder) {
        var student = new Student(data);

        studentRepository.save(student);

        var uri = uriBuilder.path("/student/{id}").buildAndExpand(student.getId()).toUri();
        return ResponseEntity.created(uri).body(new StudentRegisterReturnBody(student));
    }

    @GetMapping
    public ResponseEntity<Page<StudentRegisterReturnBody>> listAllStudents(Pageable pageable) {
        var page = studentRepository.findAllByActiveTrue(pageable).map(StudentRegisterReturnBody::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentRegisterReturnBody> getStudent(@PathVariable Long id) {
        var student = studentRepository.getReferenceById(id);
        return ResponseEntity.ok(new StudentRegisterReturnBody(student));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deactivateStudent(@PathVariable Long id) {
        var student = studentRepository.getReferenceById(id);
        student.deactivate();

        return ResponseEntity.noContent().build();
    }

}
