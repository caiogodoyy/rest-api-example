package com.caio.alura.api.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.caio.alura.api.enums.Department;
import com.caio.alura.api.model.teacher.Teacher;
import com.caio.alura.api.model.teacher.TeacherRegisterReturnBody;
import com.caio.alura.api.repository.TeacherRepository;

@Service
public class TeacherService {

    private final Logger logger = LoggerFactory.getLogger(TeacherService.class);

    @Autowired
    private TeacherRepository teacherRepository;

    public void saveTeacher(Teacher teacher) {
        logger.info(".saveTeacher: Saving teacher " + teacher.getName());
        this.teacherRepository.save(teacher);
        logger.info(".saveTeacher: Teacher " + teacher.getName() + " saved successfully");
    }

    public Page<TeacherRegisterReturnBody> getAllActiveTeachers(Pageable pageable) {
        logger.info(".getAllActiveTeachers: Getting all active teachers");
        Page<TeacherRegisterReturnBody> result = this.teacherRepository.findAllByActiveTrue(pageable)
                .map(TeacherRegisterReturnBody::new);
        logger.info(".getAllActiveTeachers: Received " + result.getTotalElements() + " active teachers");
        return result;
    }

    public Teacher getTeacherById(Long id) {
        logger.info(".getTeacherById: Getting teacher by id " + id);
        return this.teacherRepository.getReferenceById(id);
    }

    public void inactivateTeacherById(Long id) {
        logger.info(".inactivateTeacherById: Inactivating teacher with id " + id);
        var teacher = this.teacherRepository.getReferenceById(id);
        teacher.deactivate();
        logger.info(".inactivateTeacherById: Teacher inactivated successfully with id " + id);
    }

    public boolean exists(Long id) {
        logger.info(".exists: Checking if teacher with id " + id + " exists");
        return this.teacherRepository.existsById(id);
    }

    public Teacher getRandomTeacherAvailable(LocalDateTime dateTime, Department department) {
        logger.info(".getRandomTeacherAvailable: Getting random available teacher for datetime " + dateTime + " and department " + department);
        return this.teacherRepository.getRandomTeacherAvailable(dateTime, department);
    }

    public Boolean isActive(Long id) {
        logger.info(".isActive: Checking if teacher with id " + id + " is active");
        return this.teacherRepository.getReferenceById(id).getActive();
    }
}
