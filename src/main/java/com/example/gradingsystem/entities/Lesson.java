package com.example.gradingsystem.entities;

import com.example.gradingsystem.DTOs.LessonDTO;
import com.example.gradingsystem.DTOs.StudentDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@JsonIdentityInfo(scope = Lesson.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Lesson {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    @Column(name = "topic")
    private String topic;

    @Column(name = "homework")
    private String homework;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group_id;

    @Column(name = "lesson_date")
    private LocalDateTime lessonDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_lesson",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> studentAttendance;

    public LessonDTO toDto() {
        List<StudentDTO> studentAttendance = List.of();
        if (this.studentAttendance != null)
            studentAttendance = this.studentAttendance.stream().map(Student::toDto).toList();

        return new LessonDTO(
                this.id,
                teacher,
                this.topic,
                this.homework,
                group_id,
                studentAttendance,
                this.lessonDate
        );
    }
}
