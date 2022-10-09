package com.example.gradingsystem.controllers;
import com.example.gradingsystem.entities.Group;
import com.example.gradingsystem.entities.Student;
import com.example.gradingsystem.services.student.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students/")
public class StudentController {

    private StudentServiceImpl studentService;

    @Autowired
    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "add/")
    public Student addStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping
    public List<Student> findAllStudents() {
        return studentService.getStudents();
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping(path = "{id}/")
    public Student findStudentByID(@PathVariable int id) {
        return studentService.getStudent(id);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @PutMapping(path = "edit/{id}/")
    public Student updateStudent(@PathVariable int id, @RequestBody Student student) {
        student.setId(id);
        return studentService.updateStudent(student);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @DeleteMapping(path = "remove/{id}/")
    public String deleteStudent(@PathVariable int id) {
        return studentService.deleteStudent(id);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping(path = "{id}/add/robucks")
    public Student addRobucks(@PathVariable int id, @RequestBody int robucksToAdd) {
        studentService.addRobucks(id, robucksToAdd);
        return studentService.getStudent(id);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping(path = "{id}/substract/robucks")
    public Student substractRobucks(@PathVariable int id, @RequestBody int robucksToSubstract) {
        studentService.substractRobucks(id, robucksToSubstract);
        return studentService.getStudent(id);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping(path = "{id}/reset/robucks")
    public Student resetRobucks(@PathVariable int id) {
        studentService.resetRobucksAmount(id);
        return studentService.getStudent(id);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping(path = "get/{name}")
    public List<Student> findStudentsByName(@PathVariable String name) {
        return studentService.findStudentsByName(name);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{studentId}/{groupId}/")
    Student assignStudentToGroup(
            @PathVariable int studentId,
            @PathVariable int groupId
    ) {
        return studentService.assignGroup(groupId, studentId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{studentId}/remove/group/")
    Student removeStudentFromGroup(
            @PathVariable int studentId
    ) {
        return studentService.removeGroup(studentId);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER') or hasAuthority('STUDENT')")
    @GetMapping("{studentId}/robucks/")
    int getAmountOfRobucks(@PathVariable int studentId) {
        return studentService.getAmountOfRobucks(studentId);
    }

}
