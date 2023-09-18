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
    StudentRepository studentRepository;

    public void save(Student student) {
        studentRepository.save(student);
    }

    public Page<StudentRegisterReturnBody> getAllActiveStudents(Pageable pageable) {
        return studentRepository.findAllByActiveTrue(pageable).map(StudentRegisterReturnBody::new);
    }

    public Student getStudentById(Long id) {
        return studentRepository.getReferenceById(id);
    }

    public void inactivateStudentById(Long id) {
        var student = studentRepository.getReferenceById(id);
        student.deactivate();
    }

    public boolean exists(Long id) {
        return studentRepository.existsById(id);
    }

    public Boolean isActive(Long id) {
        return studentRepository.getReferenceById(id).getActive();
    }

}
