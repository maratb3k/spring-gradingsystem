package com.example.gradingsystem.services.lesson;

import com.example.gradingsystem.entities.Lesson;
import com.example.gradingsystem.entities.Student;
import com.example.gradingsystem.entities.Subject;
import com.example.gradingsystem.entities.Teacher;
import com.example.gradingsystem.repositories.LessonRepository;
import com.example.gradingsystem.repositories.StudentRepository;
import com.example.gradingsystem.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService{

    private LessonRepository lessonRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Lesson saveLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson getLesson(int id) {
        return lessonRepository.findById(id).orElseThrow(null);
    }

    @Override
    public List<Lesson> getLessonList() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson updateLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public String deleteLesson(int id) {
        lessonRepository.deleteById(id);
        return "Lesson with id #" + id + " is deleted";
    }
    @Override
    public Lesson markStudentAttendance(int lessonId, int studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (!studentOptional.isPresent()) {
            throw new RuntimeException("Student not found with ID " + studentId);
        }
        Student student = studentOptional.get();

        Optional<Lesson> lessonOptional = lessonRepository.findById(lessonId);
        if (!lessonOptional.isPresent()) {
            throw new RuntimeException("Lesson not found with ID " + lessonId);
        }
        Lesson lesson = lessonOptional.get();

        List<Student> attendance = lesson.getStudentAttendance();
        if (!attendance.contains(student)) {
            attendance.add(student);
        }
        lesson.setStudentAttendance(attendance);

        Lesson savedLesson = lessonRepository.save(lesson);
        return savedLesson;
    }

    @Override
    public long getLessonCountByTeacherAndMonth(int teacherId, int month, int year) {
        return lessonRepository.countLessonsByTeacherAndMonth(teacherId, month, year);
    }

    @Override
    public List<Lesson> findLessonsByTeacherAndMonthAndYear(int teacherId, int month, int year) {
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
        if (teacher == null) {
            throw new RuntimeException("Teacher not found");
        }
        return lessonRepository.findAllByTeacherAndMonthAndYear(teacher, month, year);
    }

    @Override
    public List<Lesson> getLessonsByTeacherAndSubjectAndMonthAndYear(int teacherId, int subjectId, int month, int year) {
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atDay(daysInMonth);
        boolean isLeapYear = Year.of(year).isLeap();
        if (month == Month.FEBRUARY.getValue() && !isLeapYear) {
            endDate = yearMonth.atDay(28);
        }
        return lessonRepository.findAllByTeacherIdAndSubjectIdAndLessonDateBetween(
                teacherId, subjectId, startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));
    }

}
