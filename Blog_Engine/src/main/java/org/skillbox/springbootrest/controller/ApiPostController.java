package org.skillbox.springbootrest.controller;

import org.skillbox.springbootrest.api.response.PostFilterMode;
import org.skillbox.springbootrest.api.response.PostsResponse;
import org.skillbox.springbootrest.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiPostController {

    private final PostService postService;

    public ApiPostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/api/post")
    public ResponseEntity<PostsResponse> post(@RequestParam(required = false) Integer offset,
                                              @RequestParam(required = false) Integer limit,
                                              @RequestParam(required = false) PostFilterMode mode) {

        return postService.getPosts(offset, limit, mode);
    }
}