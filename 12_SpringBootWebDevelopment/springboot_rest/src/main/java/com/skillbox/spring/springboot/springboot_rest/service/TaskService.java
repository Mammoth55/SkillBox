package com.skillbox.spring.springboot.springboot_rest.service;

import com.skillbox.spring.springboot.springboot_rest.model.Task;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    Task saveTask(Task task);

    Task updateTask(Task task, int id);

    Task getTask(int id);

    Task deleteTask(int id);

    void deleteAllTasks();
}