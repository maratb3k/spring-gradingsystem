package com.example.gradingsystem.entities;

import com.example.gradingsystem.DTOs.*;
import com.example.gradingsystem.enums.Role;
import com.example.gradingsystem.enums.RoleConverter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@JsonIdentityInfo(scope = User.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "roles")
    @Convert(converter = RoleConverter.class)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Admin> admins = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Teacher> teachers = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Student> students = new ArrayList<>();

    public UserDTO toDto() {

        List<AdminDTO> admins = List.of();
        if (this.admins != null)
            admins = this.admins.stream().map(Admin::toDto).toList();

        List<TeacherDTO> teachers = List.of();
        if (this.teachers != null)
            teachers = this.teachers.stream().map(Teacher::toDto).toList();

        List<StudentDTO> students = List.of();
        if (this.students != null)
            students = this.students.stream().map(Student::toDto).toList();

        return new UserDTO(
                this.id,
                this.username,
                this.password,
                role,
                admins,
                teachers,
                students
        );
    }
}
