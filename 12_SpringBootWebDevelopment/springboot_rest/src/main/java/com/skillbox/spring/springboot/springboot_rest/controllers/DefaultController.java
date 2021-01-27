package com.skillbox.spring.springboot.springboot_rest.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

@RestController
public class DefaultController {

    @RequestMapping("/")
    public String index() {
        return "Current time : " + (new Date()).toString();
    }
}