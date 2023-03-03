package com.example.gradingsystem.services.teacher;
import com.example.gradingsystem.entities.*;
import com.example.gradingsystem.exceptions.TeacherNotFoundException;
import com.example.gradingsystem.repositories.GroupRepository;
import com.example.gradingsystem.repositories.LessonRepository;
import com.example.gradingsystem.repositories.TeacherRepository;
import com.example.gradingsystem.services.lesson.LessonServiceImpl;
import com.example.gradingsystem.services.subject.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private TeacherRepository teacherRepository;
    private LessonServiceImpl lessonService;
    private SubjectServiceImpl subjectService;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, LessonServiceImpl lessonService, SubjectServiceImpl subjectService) {
        this.teacherRepository = teacherRepository;
        this.lessonService = lessonService;
        this.subjectService = subjectService;
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

    public double calculateSalary(int teacherId, int subjectId, int year, int month) {
        List<Lesson> lessons = lessonService.getLessonsByTeacherAndSubjectAndMonthAndYear(teacherId, subjectId, month, year);
        double salary = 0.0;
        Subject subject = subjectService.getSubject(subjectId);
        for (Lesson lesson : lessons) {
            int numOfStudents = lesson.getStudentAttendance().size();
            salary += subject.getLessonFeePerStudent() * numOfStudents;
        }
        return salary;
    }

}
