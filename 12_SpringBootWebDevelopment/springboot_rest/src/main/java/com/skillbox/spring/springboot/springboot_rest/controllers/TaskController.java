package com.skillbox.spring.springboot.springboot_rest.controllers;

import com.skillbox.spring.springboot.springboot_rest.dao.TaskRepository;
import com.skillbox.spring.springboot.springboot_rest.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public ResponseEntity<List<Task>> listAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return ResponseEntity.ok().body(tasks);
    }

    @GetMapping(value = "/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable("taskId") int taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        return ResponseEntity.ok().body(task.get());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody @Valid Task task) {
        Task t = taskRepository.save(task);
        return ResponseEntity.status(201).body(t);
    }

    @PutMapping(value = "/{taskId}")
    public ResponseEntity<Task> updateTask(@RequestBody @Valid Task task, @PathVariable("taskId") int taskId) {
        return ResponseEntity.ok().body(taskRepository.save(task));
    }

    @DeleteMapping(value = "/{taskId}")
    public ResponseEntity<Task> deleteTask(@PathVariable("taskId") int taskId) {
        Optional<Task> tsk = taskRepository.findById(taskId);
        taskRepository.deleteById(taskId);
        return ResponseEntity.ok().body(tsk.get());
    }

    @DeleteMapping
    public ResponseEntity<List<Task>> deleteAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        taskRepository.deleteAll();
        return ResponseEntity.ok().body(tasks);
    }
}