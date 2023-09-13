package com.alura.api.degree.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.api.degree.model.student.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    
    public Page<Student> findAllByActiveTrue(Pageable pageable);

}
