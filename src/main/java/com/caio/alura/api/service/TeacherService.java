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
    private TeacherRepository teacherRepository;

    public void save(Teacher teacher) {
        this.teacherRepository.save(teacher);
    }

    public Page<TeacherRegisterReturnBody> getAllActiveTeachers(Pageable pageable) {
        return this.teacherRepository.findAllByActiveTrue(pageable).map(TeacherRegisterReturnBody::new);
    }

    public Teacher getTeacherById(Long id) {
        return this.teacherRepository.getReferenceById(id);
    }

    public void inactivateTeacherById(Long id) {
        var teacher = this.teacherRepository.getReferenceById(id);
        teacher.deactivate();
    }

    public boolean exists(Long id) {
        return this.teacherRepository.existsById(id);
    }

    public Teacher getRandomTeacherAvailable(LocalDateTime dateTime, Department department) {
        return this.teacherRepository.getRandomTeacherAvailable(dateTime, department);
    }

    public Boolean isActive(Long id) {
        return this.teacherRepository.getReferenceById(id).getActive();
    }

}
