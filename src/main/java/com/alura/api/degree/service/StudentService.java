package com.alura.api.degree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alura.api.degree.model.student.Student;
import com.alura.api.degree.model.student.StudentRegisterReturnBody;
import com.alura.api.degree.repository.StudentRepository;

@Service
public class StudentService {
    
    @Autowired
    StudentRepository repository;

    public void save(Student student) {
        repository.save(student);
    }

    public Page<StudentRegisterReturnBody> getAllActiveStudents(Pageable pageable) {
        return repository.findAllByActiveTrue(pageable).map(StudentRegisterReturnBody::new);
    }

    public Student getStudentById(Long id) {
        return repository.getReferenceById(id);
    }

    public void inactivateStudentById(Long id) {
        var student = repository.getReferenceById(id);
        student.deactivate();
    }

    public boolean exists(Long id) {
        return repository.existsById(id);
    }

}
