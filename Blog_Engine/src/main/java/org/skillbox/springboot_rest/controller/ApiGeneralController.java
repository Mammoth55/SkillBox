package org.skillbox.springboot_rest.controller;

import org.skillbox.springboot_rest.api.response.*;
import org.skillbox.springboot_rest.service.SettingsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiGeneralController {

    private final InitResponse initResponse;
    private final SettingsService settingsService;

    public ApiGeneralController(InitResponse initResponse, SettingsService settingsService) {
        this.initResponse = initResponse;
        this.settingsService = settingsService;
    }

    @GetMapping("/init")
    public InitResponse init() {
        return initResponse;
    }

    @GetMapping("/settings")
    public SettingsResponse settings() {
        return settingsService.getGlobalSettings();
    }

    @GetMapping("/tag")
    public TagsResponse tag(@RequestParam(required = false) String query) {
        return new TagsResponse();
    }
}