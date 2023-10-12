package com.caio.alura.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.caio.alura.api.model.student.Student;
import com.caio.alura.api.model.student.StudentRegisterReturnBody;
import com.caio.alura.api.repository.StudentRepository;

@Service
public class StudentService {
    
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    public void saveStudent(Student student) {
        logger.info(".saveStudent: Saving student {}", student.getName());
        this.studentRepository.save(student);
        logger.info(".saveStudent: Student {} saved successfully", student.getName());
    }

    public Page<StudentRegisterReturnBody> getAllActiveStudents(Pageable pageable) {
        logger.info(".getAllActiveStudents: Getting all active students");
        Page<StudentRegisterReturnBody> result = this.studentRepository.findAllByActiveTrue(pageable).map(StudentRegisterReturnBody::new);
        logger.info(".getAllActiveStudents: Received {} active students", result.getTotalElements());
        return result;
    }

    public Student getStudentById(Long id) {
        logger.info(".getStudentById: Getting student by id {}", id);
        return this.studentRepository.getReferenceById(id);
    }

    public void inactivateStudentById(Long id) {
        logger.info(".inactivateStudentById: Inactivating student with id {}", id);
        var student = this.studentRepository.getReferenceById(id);
        student.deactivate();
        logger.info(".inactivateStudentById: Student inactivated successfully with id {}", id);
    }

    public boolean exists(Long id) {
        logger.info(".exists: Checking if student with id {} exists", id);
        return this.studentRepository.existsById(id);
    }

    public Boolean isActive(Long id) {
        logger.info(".isActive: Checking if student with id {} is active", id);
        return this.studentRepository.getReferenceById(id).getActive();
    }

}
