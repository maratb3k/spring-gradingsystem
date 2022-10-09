package com.example.gradingsystem.services.student;
import com.example.gradingsystem.entities.Group;
import com.example.gradingsystem.entities.Student;
import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);
    Student getStudent(int id);
    List<Student> getStudents();
    Student updateStudent(Student student);
    String deleteStudent(int id);
    void addRobucks(int studentID, int robucksToAdd);
    void substractRobucks(int studentID, int robucksToSubstract);
    void resetRobucksAmount(int id);
    public List<Student> findStudentsByName(String name);
    Student assignGroup(int groupId, int studentId);
    Student removeGroup(int id);
    int getAmountOfRobucks(int studentId);
}



