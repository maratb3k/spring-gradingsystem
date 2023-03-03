package com.example.gradingsystem.services.lesson;

import com.example.gradingsystem.entities.Lesson;
import com.example.gradingsystem.entities.Subject;
import com.example.gradingsystem.entities.Teacher;

import java.util.List;

public interface LessonService {
    Lesson saveLesson(Lesson lesson);
    Lesson getLesson(int id);
    List<Lesson> getLessonList();
    Lesson updateLesson(Lesson lesson);
    String deleteLesson(int id);
    Lesson markStudentAttendance(int studentId, int lessonId);
    long getLessonCountByTeacherAndMonth(int teacherId, int month, int year);
    List<Lesson> findLessonsByTeacherAndMonthAndYear(int teacherId, int month, int year);
    List<Lesson> getLessonsByTeacherAndSubjectAndMonthAndYear(int teacherId, int subjectId, int month, int year);
}
