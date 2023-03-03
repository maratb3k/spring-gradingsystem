package com.example.gradingsystem.repositories;

import com.example.gradingsystem.entities.Lesson;
import com.example.gradingsystem.entities.Subject;
import com.example.gradingsystem.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    @Query("SELECT COUNT(l) FROM Lesson l WHERE l.teacher.id = :teacherId AND " +
            "EXTRACT(MONTH FROM l.lessonDate) = :month AND " +
            "EXTRACT(YEAR FROM l.lessonDate) = :year")
    Long countLessonsByTeacherAndMonth(@Param("teacherId") int teacherId,
                                       @Param("month") int month,
                                       @Param("year") int year);

    @Query("SELECT l FROM Lesson l WHERE l.teacher = :teacher AND EXTRACT(MONTH FROM l.lessonDate) = :month AND EXTRACT(YEAR FROM l.lessonDate) = :year")
    List<Lesson> findAllByTeacherAndMonthAndYear(@Param("teacher") Teacher teacher, @Param("month") int month, @Param("year") int year);

    List<Lesson> findAllByTeacherIdAndSubjectIdAndLessonDateBetween(int teacherId, int subjectId, LocalDateTime startDateTime, LocalDateTime endDateTime);

}
