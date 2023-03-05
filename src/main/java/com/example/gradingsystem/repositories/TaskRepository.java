package com.example.gradingsystem.repositories;

import com.example.gradingsystem.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
