package com.skillbox.spring.springboot.springboot_rest.service;

import com.skillbox.spring.springboot.springboot_rest.exeption_handling.EntityNotFoundException;
import com.skillbox.spring.springboot.springboot_rest.model.Task;
import com.skillbox.spring.springboot.springboot_rest.repository.TaskRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTask(int id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no Task with ID = " + id + " in Database."));
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task, int id) {
        taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no Task with ID = " + id + " in Database."));
        task.setId(id);
        return taskRepository.save(task);
    }

    @Override
    public Task deleteTask(int id) {
        Task tsk = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no Task with ID = " + id + " in Database."));
        taskRepository.deleteById(id);
        return tsk;
    }

    @Override
    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }
}