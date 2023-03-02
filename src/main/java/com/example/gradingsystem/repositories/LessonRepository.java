package com.example.gradingsystem.repositories;

import com.example.gradingsystem.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
}
