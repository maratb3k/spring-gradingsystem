package com.example.gradingsystem.DTOs;
import com.example.gradingsystem.entities.Group;
import com.example.gradingsystem.entities.User;
import com.example.gradingsystem.enums.Role;
import com.example.gradingsystem.enums.StudyStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentDTO {
    private int id;
    private String name;
    private String surname;
    private int age;
    private StudyStatus studyStatus;
    private String placeOfStudy;
    private String parentName;
    private String phoneNumber;
    private User user;
    private Role role;
    private int robucks;
    private Group groupID;
    private List<LessonDTO> participatedLessons;
}


