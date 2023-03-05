package com.example.gradingsystem.controllers;

import com.example.gradingsystem.entities.Task;
import com.example.gradingsystem.services.task.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/")
public class TaskController {

    private TaskServiceImpl taskService;

    @Autowired
    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "add/")
    public Task addTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping
    public List<Task> findTasks() {
        return taskService.getTasks();
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping(path = "{id}/")
    public Task findTaskByID(@PathVariable int id) {
        return taskService.getTask(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "edit/{id}/")
    public Task updateTask(@PathVariable int id, @RequestBody Task task) {
        task.setId(id);
        return taskService.updateTask(task);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path = "remove/{id}/")
    public String deleteTask(@PathVariable int id) {
        return taskService.deleteTask(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{taskId}/{teacherId}/")
    Task assignTeacherToTask(
            @PathVariable int taskId,
            @PathVariable int teacherId
    ) {
        return taskService.assignTeacher(taskId, teacherId);
    }
}
