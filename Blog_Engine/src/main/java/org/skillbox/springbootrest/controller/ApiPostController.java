package org.skillbox.springbootrest.controller;

import org.skillbox.springbootrest.api.response.PostFilterMode;
import org.skillbox.springbootrest.api.response.PostsResponse;
import org.skillbox.springbootrest.api.response.TagsResponse;
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
    public ResponseEntity<PostsResponse> getPosts(@RequestParam(required = false) Integer offset,
                                                  @RequestParam(required = false) Integer limit,
                                                  @RequestParam(required = false) PostFilterMode mode) {

        return postService.getPosts(offset, limit, mode, null, null);
    }

    @GetMapping("/api/post/search")
    public ResponseEntity<PostsResponse> searchPosts(@RequestParam(required = false) Integer offset,
                                                     @RequestParam(required = false) Integer limit,
                                                     @RequestParam(required = false) String query) {
        return postService.getPosts(offset, limit, PostFilterMode.recent, query, null);
    }

    @GetMapping("/api/post/byDate")
    public ResponseEntity<PostsResponse> searchPostsByDate(@RequestParam(required = false) Integer offset,
                                                           @RequestParam(required = false) Integer limit,
                                                           @RequestParam(required = false) String date) {
        return postService.getPosts(offset, limit, PostFilterMode.recent, null, date);
    }
}