package com.example.gradingsystem.DTOs;
import com.example.gradingsystem.entities.Subject;
import com.example.gradingsystem.entities.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GroupDTO {
    private int id;
    private Subject subject;
    private DayOfWeek dayOfWeek;
    private LocalTime groupTime;
    private Teacher teacher;
    private List<StudentDTO> students;
}
