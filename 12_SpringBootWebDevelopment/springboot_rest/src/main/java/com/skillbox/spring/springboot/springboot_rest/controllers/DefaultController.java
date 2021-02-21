package com.skillbox.spring.springboot.springboot_rest.controllers;

import com.skillbox.spring.springboot.springboot_rest.model.Task;
import com.skillbox.spring.springboot.springboot_rest.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class DefaultController {

    private final TaskService taskService;

    public DefaultController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Task> allTasks = taskService.getAllTasks();
        model.addAttribute("tasksCount", allTasks.size());
        return "index";
    }
}