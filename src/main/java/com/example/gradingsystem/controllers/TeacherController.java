package com.example.gradingsystem.controllers;
import com.example.gradingsystem.entities.Group;
import com.example.gradingsystem.entities.Teacher;
import com.example.gradingsystem.services.teacher.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers/")
public class TeacherController {
    private TeacherServiceImpl teacherService;

    @Autowired
    public TeacherController(TeacherServiceImpl teacherService) {
        this.teacherService = teacherService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "add/")
    public Teacher addTeacher(@RequestBody Teacher teacher) {
        return teacherService.saveTeacher(teacher);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<Teacher> findAllTeachers() {
        return teacherService.getTeachers();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "{id}/")
    public Teacher findTeacherByID(@PathVariable int id) {
        return teacherService.getTeacher(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "edit/{id}/")
    public Teacher updateTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        teacher.setId(id);
        return teacherService.updateTeacher(teacher);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path = "remove/{id}/")
    public String deleteTeacher(@PathVariable int id) {
        return teacherService.deleteTeacher(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "get/{name}/")
    public List<Teacher> findTeachersByName(@PathVariable String name) {
        return teacherService.findTeachersByName(name);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping("{id}/groups/")
    List<Group> getTeacherGroupsList(
            @PathVariable int id
    ) {
        return teacherService.getGroupList(id);
    }

}
