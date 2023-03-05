package com.example.gradingsystem.DTOs;

import com.example.gradingsystem.entities.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskDTO {
    private int id;
    private String name;
    private String description;
    private Teacher teacher;
    private LocalDateTime deadline;
    private boolean isCompleted;
}
