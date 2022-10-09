package com.example.gradingsystem.entities;

import com.example.gradingsystem.DTOs.GroupDTO;
import com.example.gradingsystem.DTOs.StudentDTO;
import com.example.gradingsystem.DTOs.TeacherDTO;
import com.example.gradingsystem.enums.DayConverter;
import com.example.gradingsystem.enums.RoleConverter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@JsonIdentityInfo(scope = Group.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Group {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    @Column(name = "day_of_week")
    @Convert(converter = DayConverter.class)
    private DayOfWeek dayOfWeek;

    @Column(name = "group_time")
    private LocalTime groupTime;
    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    @JsonIgnore
    @OneToMany(mappedBy = "groupID")
    private List<Student> students;

    public GroupDTO toDto() {
        List<StudentDTO> students = List.of();
        if (this.students != null)
            students = this.students.stream().map(Student::toDto).toList();

        return new GroupDTO(
                this.id,
                subject,
                this.dayOfWeek,
                this.groupTime,
                teacher,
                students
        );
    }
}
