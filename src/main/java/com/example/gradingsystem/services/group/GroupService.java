package com.example.gradingsystem.services.group;
import com.example.gradingsystem.entities.Group;
import com.example.gradingsystem.entities.Student;

import java.util.List;

public interface GroupService {
    Group saveGroup(Group group);
    Group getGroup(int id);
    List<Group> getGroupList();
    Group updateGroup(Group group);
    String deleteGroup(int id);
    Group assignTeacher(int teacherId, int groupId);
    Group assignSubject(int subjectId, int groupId);
    List<Student> getStudentsList(int id);
}
