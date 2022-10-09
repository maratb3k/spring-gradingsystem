package com.example.gradingsystem.entities;

import com.example.gradingsystem.DTOs.AdminDTO;
import com.example.gradingsystem.DTOs.StudentDTO;
import com.example.gradingsystem.enums.Role;
import com.example.gradingsystem.enums.RoleConverter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "admins")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@JsonIdentityInfo(scope = Admin.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Admin {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Column(name = "role")
//    @Enumerated(EnumType.STRING)
    @Convert(converter = RoleConverter.class)
    private Role role;


    public AdminDTO toDto() {
        return new AdminDTO(
                this.id,
                this.name,
                this.surname,
                user,
                this.role
        );
    }
}
