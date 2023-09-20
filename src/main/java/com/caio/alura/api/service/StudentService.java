package com.caio.alura.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.caio.alura.api.model.student.Student;
import com.caio.alura.api.model.student.StudentRegisterReturnBody;
import com.caio.alura.api.repository.StudentRepository;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;

    public void save(Student student) {
        this.studentRepository.save(student);
    }

    public Page<StudentRegisterReturnBody> getAllActiveStudents(Pageable pageable) {
        return this.studentRepository.findAllByActiveTrue(pageable).map(StudentRegisterReturnBody::new);
    }

    public Student getStudentById(Long id) {
        return this.studentRepository.getReferenceById(id);
    }

    public void inactivateStudentById(Long id) {
        var student = this.studentRepository.getReferenceById(id);
        student.deactivate();
    }

    public boolean exists(Long id) {
        return this.studentRepository.existsById(id);
    }

    public Boolean isActive(Long id) {
        return this.studentRepository.getReferenceById(id).getActive();
    }

}
