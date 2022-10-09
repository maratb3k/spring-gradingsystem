package com.example.gradingsystem.controllers;
import com.example.gradingsystem.entities.Group;
import com.example.gradingsystem.entities.Student;
import com.example.gradingsystem.entities.Teacher;
import com.example.gradingsystem.services.group.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/groups/")
public class GroupController {

    private GroupServiceImpl groupService;

    @Autowired
    public GroupController(GroupServiceImpl groupService) {
        this.groupService = groupService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "add/")
    public Group addGroup(@RequestBody Group group) {
        return groupService.saveGroup(group);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping
    public List<Group> findAllGroups() {
        return groupService.getGroupList();
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping(path = "{id}/")
    public Group findGroupByID(@PathVariable int id) {
        return groupService.getGroup(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "edit/{id}/")
    public Group updateGroup(@PathVariable int id, @RequestBody Group group) {
        group.setId(id);
        return groupService.updateGroup(group);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path = "remove/{id}/")
    public String deleteGroup(@PathVariable int id) {
        return groupService.deleteGroup(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{groupId}/{teacherId}/")
    Group assignTeacherToGroup(
            @PathVariable int teacherId,
            @PathVariable int groupId
    ) {
        return groupService.assignTeacher(teacherId, groupId);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping("{id}/students/")
    List<Student> getStudentsList(
            @PathVariable int id
    ) {
        return groupService.getStudentsList(id);
    }

}
