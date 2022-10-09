package com.example.gradingsystem.DTOs;
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
public class UserDTO {
    private int id;
    private String username;
    private String password;
    private Role role;
    private List<AdminDTO> admins;
    private List<TeacherDTO> teachers;
    private List<StudentDTO> students;
}
