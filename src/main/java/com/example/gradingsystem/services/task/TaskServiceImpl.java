package com.example.gradingsystem.services.task;

import com.example.gradingsystem.entities.Group;
import com.example.gradingsystem.entities.Task;
import com.example.gradingsystem.entities.Teacher;
import com.example.gradingsystem.repositories.TaskRepository;
import com.example.gradingsystem.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private TeacherRepository teacherRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TeacherRepository teacherRepository) {
        this.taskRepository = taskRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task getTask(int id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public String deleteTask(int id) {
        taskRepository.deleteById(id);
        return "Task deleted " + id;
    }

    @Override
    public Task assignTeacher(int taskId, int teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).get();
        Task task = taskRepository.findById(taskId).get();
        task.setTeacher(teacher);
        return taskRepository.save(task);
    }

}
