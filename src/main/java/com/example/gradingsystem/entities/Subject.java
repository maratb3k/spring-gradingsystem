package com.example.gradingsystem.entities;


import com.example.gradingsystem.DTOs.GroupDTO;
import com.example.gradingsystem.DTOs.LessonDTO;
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

    @Column(name = "lesson_fee_per_student")
    private Integer lessonFeePerStudent;

    @JsonIgnore
    @OneToMany(mappedBy = "subject")
    private List<Group> groups = new ArrayList<>();

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Lesson> lessons = new ArrayList<>();

    public SubjectDTO toDto() {
        List<GroupDTO> groups = List.of();
        if (this.groups != null)
            groups = this.groups.stream().map(Group::toDto).toList();

        List<LessonDTO> lessons = List.of();
        if (this.lessons != null)
            lessons = this.lessons.stream().map(Lesson::toDto).toList();

        return new SubjectDTO(
                this.id,
                this.name,
                this.lessonFeePerStudent,
                groups,
                lessons
        );
    }
}
