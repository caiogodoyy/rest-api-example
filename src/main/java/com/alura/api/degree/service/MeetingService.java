package com.alura.api.degree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.api.degree.model.meeting.Meeting;
import com.alura.api.degree.model.meeting.MeetingRepository;
import com.alura.api.degree.model.meeting.ScheduleMeetingData;
import com.alura.api.degree.model.teacher.Teacher;
import com.alura.api.degree.validation.meeting.MeetingValidation;

import jakarta.validation.ValidationException;

@Service
public class MeetingService {

    @Autowired
    MeetingRepository meetingRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    List<MeetingValidation> meetingValidations;

    public Meeting schedule(ScheduleMeetingData data) {
        if (data.teacherId() != null && !teacherService.exists(data.teacherId())) {
            throw new ValidationException("Teacher do not exists");
        }
        if (!studentService.exists(data.studentId())) {
            throw new ValidationException("Student do not exists");
        }

        meetingValidations.forEach(v -> v.validate(data));

        var teacher = getTeacher(data);
        var student = studentService.getStudentById(data.studentId());
        var meeting = new Meeting(null, teacher, student, data.dateTime());

        meetingRepository.save(meeting);

        return meeting;
    }

    public Teacher getTeacher(ScheduleMeetingData data) {
        if (data.teacherId() != null) {
            return teacherService.getTeacherById(data.teacherId());
        }
        if (data.department() == null) {
            throw new ValidationException("Department should not be empty when teacher is not given");
        }
        return teacherService.getRandomTeacherAvailable(data.dateTime(), data.department());
    }

}
