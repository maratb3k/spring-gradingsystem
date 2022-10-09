package com.example.gradingsystem.DTOs;
import com.example.gradingsystem.entities.User;
import com.example.gradingsystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminDTO {
    private int id;
    private String name;
    private String surname;
    private User user;
    private Role role;
}
