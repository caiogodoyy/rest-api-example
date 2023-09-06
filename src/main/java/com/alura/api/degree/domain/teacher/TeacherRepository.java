package com.alura.api.degree.domain.teacher;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    public Page<Teacher> findAllByActiveTrue(Pageable pageable);
    
}
