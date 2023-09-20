package com.caio.alura.api.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.caio.alura.api.enums.Department;
import com.caio.alura.api.enums.Gender;
import com.caio.alura.api.model.address.AddressRegisterData;
import com.caio.alura.api.model.meeting.Meeting;
import com.caio.alura.api.model.student.Student;
import com.caio.alura.api.model.student.StudentRegisterData;
import com.caio.alura.api.model.teacher.Teacher;
import com.caio.alura.api.model.teacher.TeacherRegisterData;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class TeacherRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    void returnNullWhenThereIsNoTeacherAvailable() {
        var dateTime = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).withHour(10).withNano(0);
        System.out.println("Data atual" + dateTime);

        var teacher = registerTeacher("teacher", "teacher@email.com", "geografia");
        var student = registerStudent("student", "student@email.com", "6 ano");
        scheduleMeeting(teacher, student, dateTime);

        var teacherReturned = this.teacherRepository.getRandomTeacherAvailable(dateTime, Department.GEOGRAFIA);
        assertEquals(null, teacherReturned);
    }

    @Test
    void returnRandomTeacherWhenTeacherIsAvailable() {
        var dateTime = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).withHour(10).withNano(0);
        System.out.println("Data atual" + dateTime);

        var teacher = registerTeacher("teacher", "teacher@email.com", "geografia");

        var teacherReturned = this.teacherRepository.getRandomTeacherAvailable(dateTime, Department.GEOGRAFIA);
        assertEquals(teacher, teacherReturned);
    }

    @Test
    void returnNullWhenTeacherIsAvailableWithDifferentDeparment() {
        var dateTime = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).withHour(10).withNano(0);
        System.out.println("Data atual" + dateTime);

        registerTeacher("teacher", "teacher@email.com", "geografia");

        var teacherReturned = this.teacherRepository.getRandomTeacherAvailable(dateTime, Department.MATEMATICA);
        assertEquals(null, teacherReturned);
    }

    @Test
    void returnNullWhenAllTeachersAreInactive() {
        var dateTime = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).withHour(10).withNano(0);
        System.out.println("Data atual" + dateTime);

        var teacher = registerTeacher("teacher", "teacher@email.com", "geografia");
        teacher.deactivate();

        var teacherReturned = this.teacherRepository.getRandomTeacherAvailable(dateTime, Department.GEOGRAFIA);
        assertEquals(null, teacherReturned);
    }

    private Teacher registerTeacher(String name, String email, String department) {
        var teacher = new Teacher(teacherRegisterData(name, email, department));
        this.entityManager.persist(teacher);
        return teacher;
    }

    //

    private TeacherRegisterData teacherRegisterData(String name, String email, String department) {
        return new TeacherRegisterData(name, email, Gender.MASCULINO.toString(), department, 1500.00, "(99)99999-9999",
                addressRegisterData());
    }

    private Student registerStudent(String name, String email, String grade) {
        var student = new Student(studentRegisterData(name, email, grade));
        this.entityManager.persist(student);
        return student;
    }

    private StudentRegisterData studentRegisterData(String name, String email, String grade) {
        return new StudentRegisterData(name, email, Gender.MASCULINO.toString(), grade, "(99)99999-9999",
                addressRegisterData());
    }

    private AddressRegisterData addressRegisterData() {
        return new AddressRegisterData("99999999", "State", "City", "Street");
    }

    private void scheduleMeeting(Teacher teacher, Student student, LocalDateTime dateTime) {
        this.entityManager.persist(new Meeting(null, teacher, student, dateTime));
    }

}
