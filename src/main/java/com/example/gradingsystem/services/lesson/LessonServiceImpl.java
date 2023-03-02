package com.example.gradingsystem.services.lesson;

import com.example.gradingsystem.entities.Lesson;
import com.example.gradingsystem.entities.Student;
import com.example.gradingsystem.repositories.LessonRepository;
import com.example.gradingsystem.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService{

    private LessonRepository lessonRepository;
    private StudentRepository studentRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository, StudentRepository studentRepository) {
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
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
}
