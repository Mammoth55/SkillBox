package com.skillbox.spring.springboot.springboot_rest.controllers;

import com.skillbox.spring.springboot.springboot_rest.model.Task;
import com.skillbox.spring.springboot.springboot_rest.service.TaskService;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@Component
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String listAllTasks(Model model) {
        List<Task> allTasks = taskService.getAllTasks();
        model.addAttribute("allTasks", allTasks);
        model.addAttribute("tasksCount", allTasks.size());
        return "listAllTasks";
    }

    @GetMapping(value = "/{taskId}")
    public Task getTask(@PathVariable("taskId") int taskId) {
        return taskService.getTask(taskId);
    }

    @PostMapping
    public Task createTask(@RequestBody @Valid Task task) {
        return taskService.saveTask(task);
    }

    @PutMapping(value = "/{taskId}")
    public Task updateTask(@RequestBody @Valid Task task, @PathVariable("taskId") int taskId) {
        return taskService.updateTask(task, taskId);
    }

    @DeleteMapping(value = "/{taskId}")
    public Task deleteTask(@PathVariable("taskId") int taskId) {
        return taskService.deleteTask(taskId);
    }

    @DeleteMapping
    public void deleteAllTasks() {
        taskService.deleteAllTasks();
    }
}