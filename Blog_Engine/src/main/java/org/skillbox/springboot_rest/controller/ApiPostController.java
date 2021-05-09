package org.skillbox.springboot_rest.controller;

import org.skillbox.springboot_rest.api.response.PostFilterMode;
import org.skillbox.springboot_rest.api.response.PostsResponse;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiPostController {

    @GetMapping("/api/post")
    public PostsResponse post(@RequestParam(required = false) int offset,
                              @RequestParam(required = false) int limit,
                              @RequestParam(required = false) PostFilterMode mode) {
        return new PostsResponse();
    }
}