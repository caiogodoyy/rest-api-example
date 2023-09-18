package com.alura.api.degree.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alura.api.degree.model.teacher.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    public Page<Teacher> findAllByActiveTrue(Pageable pageable);

    @Query("""
            select t from Teacher t
            where
            t.active = true
            and
            t.department = :department
            and
            t.id not in(
                select m.teacher.id from Meeting m
                where
                m.dateTime = :dateTime
            )
            order by rand()
            limit 1
            """)
    public Teacher getRandomTeacherAvailable(LocalDateTime dateTime, String department);

}
