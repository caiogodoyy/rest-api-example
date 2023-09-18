package com.alura.api.degree.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.api.degree.model.meeting.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    public boolean existsByTeacherIdAndDateTime(Long teacherId, LocalDateTime dateTime);

    public boolean existsByStudentIdAndDateTimeBetween(Long studentId, LocalDateTime begin, LocalDateTime end);
    
}
