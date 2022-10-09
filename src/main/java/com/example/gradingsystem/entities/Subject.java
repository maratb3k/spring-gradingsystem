package com.example.gradingsystem.entities;


import com.example.gradingsystem.DTOs.GroupDTO;
import com.example.gradingsystem.DTOs.SubjectDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@JsonIdentityInfo(scope = Subject.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Subject {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "subject")
    private List<Group> groups = new ArrayList<>();

    public SubjectDTO toDto() {
        List<GroupDTO> groups = List.of();
        if (this.groups != null)
            groups = this.groups.stream().map(Group::toDto).toList();

        return new SubjectDTO(
                this.id,
                this.name,
                groups
        );
    }
}
