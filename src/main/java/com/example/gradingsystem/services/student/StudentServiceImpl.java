package com.example.gradingsystem.services.student;

import com.example.gradingsystem.entities.Group;
import com.example.gradingsystem.entities.Student;
import com.example.gradingsystem.entities.Teacher;
import com.example.gradingsystem.exceptions.RobucksExceededException;
import com.example.gradingsystem.exceptions.StudentNotFoundException;
import com.example.gradingsystem.repositories.GroupRepository;
import com.example.gradingsystem.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private GroupRepository groupRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudent(int id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " was not found"));
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public String deleteStudent(int id) {
        Student student = getStudent(id);
        student.setGroupID(null);
        studentRepository.deleteById(id);
        return "Student deleted " + id;
    }

    @Override
    public void addRobucks(int studentID, int robucksToAdd) {
        Student student = studentRepository.findById(studentID).orElseThrow(() -> new StudentNotFoundException("Student was not found"));
        student.addRobucks(robucksToAdd);
        studentRepository.save(student);
    }

    @Override
    public void substractRobucks(int studentID, int robucksToSubstract) {
        Student student = getStudent(studentID);
        student.substractRobucks(robucksToSubstract);
        studentRepository.save(student);
    }

    @Override
    public void resetRobucksAmount(int id) {
        Student student = getStudent(id);
        student.resetRobucks();
        studentRepository.save(student);
    }

    @Override
    public List<Student> findStudentsByName(String name) {
        return studentRepository.findAllByNameContaining(name);
    }

    @Override
    public Student assignGroup(int groupId, int studentId) {
        Group group = groupRepository.findById(groupId).get();
        Student student = studentRepository.findById(studentId).get();
        student.setGroupID(group);
        return studentRepository.save(student);
    }

    @Override
    public Student removeGroup(int id) {
        Student student = getStudent(id);
        student.setGroupID(null);
        return studentRepository.save(student);
    }

    @Override
    public int getAmountOfRobucks(int studentId) {
        Student student = getStudent(studentId);
        return student.getRobucks();
    }
}
