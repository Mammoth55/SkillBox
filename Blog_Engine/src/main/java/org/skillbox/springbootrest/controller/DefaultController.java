package org.skillbox.springbootrest.controller;

import org.skillbox.springbootrest.service.GlobalSettingsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    private final GlobalSettingsService globalSettingsService;

    public DefaultController(GlobalSettingsService globalSettingsService) {
        this.globalSettingsService = globalSettingsService;
    }


    @GetMapping("/")
    public String index() {
        globalSettingsService.initGlobalSettings();
        return "index";
    }
}