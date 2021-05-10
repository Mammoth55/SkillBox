package org.skillbox.springbootrest.controller;

import org.skillbox.springbootrest.api.response.*;
import org.skillbox.springbootrest.service.GlobalSettingsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiGeneralController {

    private final InitResponse initResponse;
    private final GlobalSettingsService globalSettingsService;

    public ApiGeneralController(InitResponse initResponse, GlobalSettingsService globalSettingsService) {
        this.initResponse = initResponse;
        this.globalSettingsService = globalSettingsService;
    }

    @GetMapping("/init")
    public ResponseEntity<InitResponse> init() {
        return new ResponseEntity<>(initResponse, HttpStatus.OK);
    }

    @GetMapping("/settings")
    public ResponseEntity<GlobalSettingsResponse> settings() {
        return new ResponseEntity<>(globalSettingsService.getGlobalSettings(), HttpStatus.OK);
    }

    @GetMapping("/tag")
    public ResponseEntity<TagsResponse> tag(@RequestParam(required = false) String query) {
        return new ResponseEntity<>(new TagsResponse(), HttpStatus.OK);
    }
}