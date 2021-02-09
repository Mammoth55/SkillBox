package com.skillbox.spring.springboot.springboot_rest.controllers;

import com.skillbox.spring.springboot.springboot_rest.model.Task;
import com.skillbox.spring.springboot.springboot_rest.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DefaultController {

    private final TaskService taskService;

    public DefaultController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/hello")
    public String helloPage(Model model) {
        return "hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage(Model model) {
        return "goodbye";
    }

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("task", new Task());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Task task, Model model) {
        model.addAttribute("task", task);
        return "result";
    }
}