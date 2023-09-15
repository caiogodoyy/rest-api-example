package com.alura.api.degree.model.meeting;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.alura.api.degree.model.student.Student;
import com.alura.api.degree.model.teacher.Teacher;
import com.alura.api.degree.service.StudentService;
import com.alura.api.degree.service.TeacherService;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "meetings")
@Entity(name = "Meeting")
public class Meeting {

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    private LocalDateTime dateTime;

    public Meeting(ScheduleMeetingData data) {
        this.teacher = teacherService.getTeacherById((data.teacherId()));
        this.student = studentService.getStudentById(data.studentId());
        this.dateTime = data.dateTime();
    }

}
