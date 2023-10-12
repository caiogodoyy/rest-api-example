package com.caio.alura.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caio.alura.api.model.meeting.Meeting;
import com.caio.alura.api.model.meeting.MeetingRegisterData;
import com.caio.alura.api.model.meeting.MeetingRegisterReturnData;
import com.caio.alura.api.model.teacher.Teacher;
import com.caio.alura.api.repository.MeetingRepository;
import com.caio.alura.api.validation.meeting.MeetingValidation;

import jakarta.validation.ValidationException;

import java.util.List;

@Service
public class MeetingService {

    private final Logger logger = LoggerFactory.getLogger(MeetingService.class);

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private List<MeetingValidation> meetingValidations;

    public MeetingRegisterReturnData schedule(MeetingRegisterData data) {
        logger.info(".schedule: Scheduling meeting for studentId {} and teacherId {} at dateTime {}",
                data.studentId(), data.teacherId(), data.dateTime());

        if (data.teacherId() != null && !this.teacherService.exists(data.teacherId())) {
            logger.error(".schedule: Teacher with id {} does not exist", data.teacherId());
            throw new ValidationException("Teacher does not exist");
        }

        if (!this.studentService.exists(data.studentId())) {
            logger.error(".schedule: Student with id {} does not exist", data.studentId());
            throw new ValidationException("Student does not exist");
        }

        this.meetingValidations.forEach(v -> v.validate(data));

        var student = this.studentService.getStudentById(data.studentId());
        var teacher = getTeacher(data);

        if (teacher == null) {
            logger.error(".schedule: No teacher available for meeting");
            throw new ValidationException("There is no teacher available");
        }

        var meeting = new Meeting(null, teacher, student, data.dateTime());

        this.meetingRepository.save(meeting);

        logger.info(".schedule: Meeting scheduled successfully with id {}", meeting.getId());

        return new MeetingRegisterReturnData(meeting);
    }

    public Teacher getTeacher(MeetingRegisterData data) {
        if (data.teacherId() != null) {
            return this.teacherService.getTeacherById(data.teacherId());
        }
        if (data.department() == null) {
            logger.error(".getTeacher: Department should not be empty when teacher is not given");
            throw new ValidationException("Department should not be empty when teacher is not given");
        }
        return this.teacherService.getRandomTeacherAvailable(data.dateTime(), data.department());
    }

    public void cancel(Long id) {
        logger.info(".cancel: Canceling meeting with id {}", id);
        var meeting = this.meetingRepository.getReferenceById(id);
        this.meetingRepository.delete(meeting);
        logger.info(".cancel: Meeting canceled successfully with id {}", id);
    }
}
