package com.example.gradingsystem.services.subject;

import com.example.gradingsystem.entities.Group;
import com.example.gradingsystem.entities.Subject;
import com.example.gradingsystem.entities.Teacher;

import java.util.List;

public interface SubjectService {
    Subject saveSubject(Subject subject);
    Subject getSubject(int id);
    List<Subject> getSubjects();
    Subject updateSubject(Subject subject);
    String deleteSubject(int id);
    public List<Subject> findSubjectsByName(String name);
    List<Group> getGroupList(int id);
}
