package com.caio.alura.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.caio.alura.api.model.student.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    
    public Page<Student> findAllByActiveTrue(Pageable pageable);

}
