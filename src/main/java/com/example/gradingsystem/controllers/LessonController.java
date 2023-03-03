package com.example.gradingsystem.controllers;

import com.example.gradingsystem.entities.Lesson;
import com.example.gradingsystem.entities.Subject;
import com.example.gradingsystem.entities.Teacher;
import com.example.gradingsystem.services.lesson.LessonServiceImpl;
import com.example.gradingsystem.services.subject.SubjectServiceImpl;
import com.example.gradingsystem.services.teacher.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons/")
public class LessonController {

    private LessonServiceImpl lessonService;
    private SubjectServiceImpl subjectService;
    private TeacherServiceImpl teacherService;


    @Autowired
    public LessonController(LessonServiceImpl lessonService, SubjectServiceImpl subjectService, TeacherServiceImpl teacherService) {
        this.lessonService = lessonService;
        this.subjectService = subjectService;
        this.teacherService = teacherService;
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @PostMapping(path = "add/")
    public Lesson addLesson(@RequestBody Lesson lesson) {
        return lessonService.saveLesson(lesson);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping
    public List<Lesson> findAllLessons() {
        return lessonService.getLessonList();
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping(path = "{id}/")
    public Lesson findLessonByID(@PathVariable int id) {
        return lessonService.getLesson(id);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @PutMapping(path = "edit/{id}/")
    public Lesson updateLesson(@PathVariable int id, @RequestBody Lesson lesson) {
        lesson.setId(id);
        return lessonService.updateLesson(lesson);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @DeleteMapping(path = "remove/{id}/")
    public String deleteLesson(@PathVariable int id) {
        return lessonService.deleteLesson(id);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping("{lessonId}/{studentId}/")
    Lesson markStudentAttendanceForLesson(
            @PathVariable int lessonId,
            @PathVariable int studentId
    ) {
        return lessonService.markStudentAttendance(lessonId, studentId);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping("teachers/{teacherId}/lessons/count")
    public ResponseEntity<Long> getLessonCountByTeacherAndMonth(
            @PathVariable int teacherId,
            @RequestParam int month,
            @RequestParam int year) {

        long lessonCount = lessonService.getLessonCountByTeacherAndMonth(teacherId, month, year);

        return ResponseEntity.ok(lessonCount);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping("teacher/{teacherId}/")
    public List<Lesson> getLessonsByTeacherAndMonthAndYear(@PathVariable int teacherId, @RequestParam int month, @RequestParam int year) {
        return lessonService.findLessonsByTeacherAndMonthAndYear(teacherId, month, year);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping("teacher/{teacherId}/subject/{subjectId}/month/{month}/year/{year}/")
    public ResponseEntity<List<Lesson>> getLessonsByTeacherAndSubjectAndMonthAndYear(@PathVariable int teacherId, @PathVariable int subjectId, @PathVariable int month, @PathVariable int year) {
        List<Lesson> lessons = lessonService.getLessonsByTeacherAndSubjectAndMonthAndYear(teacherId, subjectId, month, year);
        return ResponseEntity.ok(lessons);
    }
}
