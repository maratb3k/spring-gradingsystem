package com.example.gradingsystem.services.teacher;
import com.example.gradingsystem.entities.Group;
import com.example.gradingsystem.entities.Student;
import com.example.gradingsystem.entities.Teacher;
import com.example.gradingsystem.exceptions.TeacherNotFoundException;
import com.example.gradingsystem.repositories.GroupRepository;
import com.example.gradingsystem.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher getTeacher(int id) {
        return teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException("Teacher with id " + id + " was not found"));
    }

    @Override
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public String deleteTeacher(int id) {
        Teacher teacher = getTeacher(id);
        for (Group group : teacher.getGroups()) {
            group.setTeacher(null);
        }
        teacher.getGroups().clear();
        teacherRepository.deleteById(id);
        return "Teacher deleted " + id;
    }

    @Override
    public List<Teacher> findTeachersByName(String name) {
        return teacherRepository.findAllByNameContaining(name);
    }

    @Override
    public List<Group> getGroupList(int id) {
        Teacher teacher = getTeacher(id);
        return teacher.getGroups();
    }

}
