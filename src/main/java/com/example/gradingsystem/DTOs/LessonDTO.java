package com.example.gradingsystem.DTOs;

import com.example.gradingsystem.entities.Group;
import com.example.gradingsystem.entities.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LessonDTO {
    private int id;
    private Teacher teacher;
    private String topic;
    private String homework;
    private Group group_id;
    private List<StudentDTO> studentAttendance;
    private LocalDateTime lessonDate;
}
