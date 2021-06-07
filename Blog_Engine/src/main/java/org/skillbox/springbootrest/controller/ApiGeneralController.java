package org.skillbox.springbootrest.controller;

import org.skillbox.springbootrest.api.response.CalendarResponse;
import org.skillbox.springbootrest.api.response.GlobalSettingsResponse;
import org.skillbox.springbootrest.api.response.InitResponse;
import org.skillbox.springbootrest.api.response.TagsResponse;
import org.skillbox.springbootrest.service.GlobalSettingsService;
import org.skillbox.springbootrest.service.PostService;
import org.skillbox.springbootrest.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiGeneralController {

    private static final String CURRENT_YEAR = "2021";
    private final InitResponse initResponse;
    private final GlobalSettingsService globalSettingsService;
    private final TagService tagService;
    private final PostService postService;

    public ApiGeneralController(InitResponse initResponse, GlobalSettingsService globalSettingsService,
                                TagService tagService, PostService postService) {
        this.initResponse = initResponse;
        this.globalSettingsService = globalSettingsService;
        this.tagService = tagService;
        this.postService = postService;
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
    public ResponseEntity<TagsResponse> tag(@RequestParam(name = "query", required = false, value = "query") String query) {
        return tagService.getTags(query);
    }

    @GetMapping("/calendar")
    public ResponseEntity<CalendarResponse> calendar(@RequestParam(name = "year", required = false, value = "year", defaultValue = CURRENT_YEAR) Integer year) {
        return postService.getCalendar(year);
    }
}