package com.example.gradingsystem.entities;
import com.example.gradingsystem.DTOs.GroupDTO;
import com.example.gradingsystem.DTOs.TeacherDTO;
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
@Table(name = "teachers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@JsonIdentityInfo(scope = Teacher.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Teacher {
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

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<Group> groups = new ArrayList<>();

//    @OneToMany(mappedBy = "teacher")
//    private List<Lesson> lessons = new ArrayList<>();



    public TeacherDTO toDto() {

        List<GroupDTO> groups = List.of();
        if (this.groups != null)
            groups = this.groups.stream().map(Group::toDto).toList();

        return new TeacherDTO(
                this.id,
                this.name,
                this.surname,
                user,
                this.role,
                groups
        );
    }
}
