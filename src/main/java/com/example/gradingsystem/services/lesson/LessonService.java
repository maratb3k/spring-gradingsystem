package com.example.gradingsystem.services.lesson;

import com.example.gradingsystem.entities.Lesson;

import java.util.List;

public interface LessonService {
    Lesson saveLesson(Lesson lesson);
    Lesson getLesson(int id);
    List<Lesson> getLessonList();
    Lesson updateLesson(Lesson lesson);
    String deleteLesson(int id);
    Lesson markStudentAttendance(int studentId, int lessonId);
}
