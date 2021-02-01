package com.skillbox.spring.springboot.springboot_rest.service;

import com.skillbox.spring.springboot.springboot_rest.model.Task;
import com.skillbox.spring.springboot.springboot_rest.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Task> getTask(int id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    @Override
    public ResponseEntity<Task> saveTask(Task task) {
        return ResponseEntity.status(201).body(taskRepository.save(task));
    }

    @Override
    public ResponseEntity<Task> updateTask(Task task, int id) {
        Optional<Task> tsk = taskRepository.findById(id);
        if (! tsk.isPresent()) {
            return ResponseEntity.status(404).body(null);
        }
        task.setId(id);
        return ResponseEntity.ok().body(taskRepository.save(task));
    }

    @Override
    public ResponseEntity<Task> deleteTask(int id) {
        Optional<Task> tsk = taskRepository.findById(id);
        if (! tsk.isPresent()) {
            return ResponseEntity.status(404).body(null);
        }
        taskRepository.deleteById(id);
        return ResponseEntity.ok().body(tsk.get());
    }

    @Override
    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }
}