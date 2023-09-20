package com.caio.alura.api.service;

import java.time.LocalDateTime;

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

    @Autowired
    TeacherRepository teacherRepository;

    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public Page<TeacherRegisterReturnBody> getAllActiveTeachers(Pageable pageable) {
        return teacherRepository.findAllByActiveTrue(pageable).map(TeacherRegisterReturnBody::new);
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.getReferenceById(id);
    }

    public void inactivateTeacherById(Long id) {
        var teacher = teacherRepository.getReferenceById(id);
        teacher.deactivate();
    }

    public boolean exists(Long id) {
        return teacherRepository.existsById(id);
    }

    public Teacher getRandomTeacherAvailable(LocalDateTime dateTime, Department department) {
        return teacherRepository.getRandomTeacherAvailable(dateTime, department);
    }

    public Boolean isActive(Long id) {
        return teacherRepository.getReferenceById(id).getActive();
    }

}
