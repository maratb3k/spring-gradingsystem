package com.example.gradingsystem.services.task;

import com.example.gradingsystem.entities.Task;

import java.util.List;

public interface TaskService {
    Task saveTask(Task task);
    Task getTask(int id);
    List<Task> getTasks();
    Task updateTask(Task task);
    String deleteTask(int id);
    Task assignTeacher(int taskId, int teacherId);
}
