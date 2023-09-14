package com.alura.api.degree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alura.api.degree.model.teacher.Teacher;
import com.alura.api.degree.model.teacher.TeacherRegisterReturnBody;
import com.alura.api.degree.repository.TeacherRepository;

@Service
public class TeacherService {

    @Autowired
    TeacherRepository repository;

    public void save(Teacher teacher) {
        repository.save(teacher);
    }

    public Page<TeacherRegisterReturnBody> getAllActiveTeachers(Pageable pageable) {
        return repository.findAllByActiveTrue(pageable).map(TeacherRegisterReturnBody::new);
    }

    public Teacher getTeacherById(Long id) {
        return repository.getReferenceById(id);
    }

    public void inactivateTeacherById(Long id) {
        var teacher = repository.getReferenceById(id);
        teacher.deactivate();
    }

}
