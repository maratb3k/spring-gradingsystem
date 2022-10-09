package com.example.gradingsystem.services.group;
import com.example.gradingsystem.entities.Group;
import com.example.gradingsystem.entities.Student;
import com.example.gradingsystem.entities.Subject;
import com.example.gradingsystem.entities.Teacher;
import com.example.gradingsystem.exceptions.GroupNotFoundException;
import com.example.gradingsystem.repositories.GroupRepository;
import com.example.gradingsystem.repositories.SubjectRepository;
import com.example.gradingsystem.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;
    private TeacherRepository teacherRepository;
    private SubjectRepository subjectRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, TeacherRepository teacherRepository,
                            SubjectRepository subjectRepository) {
        this.groupRepository = groupRepository;
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group getGroup(int id) {
        return groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException("Group with id " + id + " was not found"));
    }

    @Override
    public List<Group> getGroupList() {
        return groupRepository.findAll();
    }

    @Override
    public Group updateGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public String deleteGroup(int id) {
        Group group = getGroup(id);
        for (Student student : group.getStudents()) {
            student.setGroupID(null);
        }
        group.getStudents().clear();
        group.setTeacher(null);
        group.setSubject(null);
        groupRepository.deleteById(id);
        return "Group deleted " + id;
    }

    @Override
    public Group assignTeacher(int teacherId, int groupId) {
        Teacher teacher = teacherRepository.findById(teacherId).get();
        Group group = groupRepository.findById(groupId).get();
        group.setTeacher(teacher);
        return groupRepository.save(group);
    }

    @Override
    public Group assignSubject(int subjectId, int groupId) {
        Subject subject = subjectRepository.findById(subjectId).get();
        Group group = groupRepository.findById(groupId).get();
        group.setSubject(subject);
        return groupRepository.save(group);
    }

    @Override
    public List<Student> getStudentsList(int id) {
        Group group = getGroup(id);
        return group.getStudents();
    }
}
