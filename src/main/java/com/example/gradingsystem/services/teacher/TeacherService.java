package com.example.gradingsystem.services.teacher;

import com.example.gradingsystem.entities.Group;
import com.example.gradingsystem.entities.Student;
import com.example.gradingsystem.entities.Subject;
import com.example.gradingsystem.entities.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher saveTeacher(Teacher teacher);
    Teacher getTeacher(int id);
    List<Teacher> getTeachers();
    Teacher updateTeacher(Teacher teacher);
    String deleteTeacher(int id);
    public List<Teacher> findTeachersByName(String name);
    List<Group> getGroupList(int id);
    double calculateSalary(int teacherId, int subjectId, int year, int month);
}
