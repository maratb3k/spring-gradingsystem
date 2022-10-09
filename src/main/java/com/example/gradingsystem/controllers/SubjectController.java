package com.example.gradingsystem.controllers;

import com.example.gradingsystem.entities.Group;
import com.example.gradingsystem.entities.Subject;
import com.example.gradingsystem.entities.Teacher;
import com.example.gradingsystem.services.subject.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects/")
public class SubjectController {

    private SubjectServiceImpl subjectService;

    @Autowired
    public SubjectController(SubjectServiceImpl subjectService) {
        this.subjectService = subjectService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "add/")
    public Subject addSubject(@RequestBody Subject subject) {
        return subjectService.saveSubject(subject);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping
    public List<Subject> findAllSubjects() {
        return subjectService.getSubjects();
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping(path = "{id}/")
    public Subject findSubjectByID(@PathVariable int id) {
        return subjectService.getSubject(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "edit/{id}/")
    public Subject updateSubject(@PathVariable int id, @RequestBody Subject subject) {
        subject.setId(id);
        return subjectService.updateSubject(subject);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path = "remove/{id}/")
    public String deleteSubject(@PathVariable int id) {
        return subjectService.deleteSubject(id);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping(path = "get/{name}/")
    public List<Subject> findSubjectsByName(@PathVariable String name) {
        return subjectService.findSubjectsByName(name);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping("{id}/groups/")
    List<Group> getSubjectGroupsList(
            @PathVariable int id
    ) {
        return subjectService.getGroupList(id);
    }

}
