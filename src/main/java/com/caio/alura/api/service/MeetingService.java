package com.caio.alura.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caio.alura.api.model.meeting.Meeting;
import com.caio.alura.api.model.meeting.ScheduleMeetingData;
import com.caio.alura.api.model.teacher.Teacher;
import com.caio.alura.api.repository.MeetingRepository;
import com.caio.alura.api.validation.meeting.MeetingValidation;

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

        var student = studentService.getStudentById(data.studentId());
        var teacher = getTeacher(data);

        if (teacher == null) {
            throw new ValidationException("There is no teacher available");
        }

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

    public void cancel(Long id) {
        var meeting = meetingRepository.getReferenceById(id);
        meetingRepository.delete(meeting);
    }

}
