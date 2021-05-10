package org.skillbox.springbootrest.controller;

import org.skillbox.springbootrest.api.response.PostFilterMode;
import org.skillbox.springbootrest.api.response.PostsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiPostController {

    @GetMapping("/api/post")
    public ResponseEntity<PostsResponse> post(@RequestParam(required = false) int offset,
                                              @RequestParam(required = false) int limit,
                                              @RequestParam(required = false) PostFilterMode mode) {
        return new ResponseEntity<>(new PostsResponse(), HttpStatus.OK);
    }
}