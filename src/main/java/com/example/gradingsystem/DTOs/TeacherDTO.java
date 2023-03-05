package com.example.gradingsystem.DTOs;
import com.example.gradingsystem.entities.User;
import com.example.gradingsystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeacherDTO {
    private int id;
    private String name;
    private String surname;
    private User user;
    private Role role;
    private List<GroupDTO> groups;
    private List<LessonDTO> lessonsTaught;
    private List<TaskDTO> tasks;
}
