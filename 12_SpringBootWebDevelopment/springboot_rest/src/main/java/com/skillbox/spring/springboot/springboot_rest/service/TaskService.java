package com.skillbox.spring.springboot.springboot_rest.service;

import com.skillbox.spring.springboot.springboot_rest.model.Task;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    ResponseEntity<Task> saveTask(Task task);

    ResponseEntity<Task> updateTask(Task task, int id);

    ResponseEntity<Task> getTask(int id);

    ResponseEntity<Task> deleteTask(int id);

    void deleteAllTasks();
}