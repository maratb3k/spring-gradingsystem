package com.example.gradingsystem.repositories;

import com.example.gradingsystem.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    public List<Subject> findAllByNameContaining(String name);
}
