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

import com.caio.alura.api.model.student.Student;
import com.caio.alura.api.model.student.StudentRegisterData;
import com.caio.alura.api.model.student.StudentRegisterReturnBody;
import com.caio.alura.api.model.student.StudentUserInputData;
import com.caio.alura.api.service.StudentService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
@SecurityRequirement(name = "bearer-key")
public class StudentController {

    private final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @PostMapping
    @Transactional
    public ResponseEntity<StudentRegisterReturnBody> registerStudent(@RequestBody @Valid StudentUserInputData userInput) {
        logger.info(".registerStudent: Received POST request for /student");
        
        var data = new StudentRegisterData(userInput);

        var student = new Student(data);

        this.studentService.saveStudent(student);

        var uri = URI.create("/student/" + student.getId());

        logger.info(".registerStudent: Responded with 201 status for POST request to /student");
        return ResponseEntity.created(uri).body(new StudentRegisterReturnBody(student));
    }

    @GetMapping
    public ResponseEntity<Page<StudentRegisterReturnBody>> listAllStudents(Pageable pageable) {
        logger.info(".listAllStudents: Received GET request for /student");

        var page = this.studentService.getAllActiveStudents(pageable);

        logger.info(".listAllTeachers: Responded with 200 status for GET request to /student");
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentRegisterReturnBody> getStudent(@PathVariable Long id) {
        logger.info(".getStudent: Received GET request for /student/" + id);

        var student = this.studentService.getStudentById(id);

        logger.info(".getStudent: Responded with 200 status for GET request to /student/" + id);
        return ResponseEntity.ok(new StudentRegisterReturnBody(student));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> inactivateStudent(@PathVariable Long id) {
        logger.info(".inactivateStudent: Received DELETE request for /student/{}", id);

        this.studentService.inactivateStudentById(id);

        logger.info(".inactivateStudent: Responded with 203 status for DELETE request to /student/" + id);
        return ResponseEntity.noContent().build();
    }

}
