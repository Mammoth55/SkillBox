package com.skillbox.spring.springboot.springboot_rest.controllers;

import com.skillbox.spring.springboot.springboot_rest.model.Task;
import com.skillbox.spring.springboot.springboot_rest.service.TaskService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
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

    @GetMapping("/{taskId}")
    public String listTaskById(@PathVariable("taskId") int taskId, Model model) {
        model.addAttribute("task", taskService.getTask(taskId));
        return "listTaskById";
    }

    @GetMapping("/new")
    public String newTask(@ModelAttribute("task") Task task) {
        return "newTask";
    }

    @PostMapping
    public String createTask(@ModelAttribute("task") @Valid Task task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "newTask";
        }
        taskService.saveTask(task);
        return "redirect:/";
    }

    @GetMapping("/{taskId}/edit")
    public String editTask(Model model, @PathVariable("taskId") int taskId) {
        model.addAttribute("task", taskService.getTask(taskId));
        return "editTask";
    }

    @PatchMapping("/{taskId}")
    public String updateTask(@PathVariable("taskId") int taskId, @ModelAttribute("task") @Valid Task task,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editTask";
        }
        taskService.updateTask(task, taskId);
        return "redirect:/";
    }

    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable("taskId") int taskId) {
        taskService.deleteTask(taskId);
        return "redirect:/";
    }

    @DeleteMapping
    public String deleteAllTasks() {
        taskService.deleteAllTasks();
        return "redirect:/";
    }
}