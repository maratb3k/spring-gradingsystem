package com.example.gradingsystem.controllers;

import com.example.gradingsystem.entities.Lesson;
import com.example.gradingsystem.services.lesson.LessonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons/")
public class LessonController {

    private LessonServiceImpl lessonService;


    @Autowired
    public LessonController(LessonServiceImpl lessonService) {
        this.lessonService = lessonService;
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
}
