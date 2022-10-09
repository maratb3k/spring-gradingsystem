package com.example.gradingsystem.repositories;
import com.example.gradingsystem.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    public List<Teacher> findAllByNameContaining(String name);
}
