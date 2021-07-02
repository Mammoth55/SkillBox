package org.skillbox.springbootrest.controller;

import org.skillbox.springbootrest.api.response.FullPostResponse;
import org.skillbox.springbootrest.api.response.PostFilterMode;
import org.skillbox.springbootrest.api.response.PrePostsResponse;
import org.skillbox.springbootrest.service.PostService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class ApiPostController {

    private final PostService postService;

    public ApiPostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/api/post")
    public ResponseEntity<PrePostsResponse> getPosts(@RequestParam(name = "offset", required = false, value = "offset", defaultValue = "0") int offset,
                                                     @RequestParam(name = "limit", required = false, value = "limit", defaultValue = "10") int limit,
                                                     @RequestParam(name = "mode", required = false, value = "mode", defaultValue = "recent") PostFilterMode mode) {
        return postService.getAllPosts(offset, limit, mode);
    }

    @GetMapping("/api/post/search")
    public ResponseEntity<PrePostsResponse> searchPosts(@RequestParam(name = "offset", required = false, value = "offset", defaultValue = "0") int offset,
                                                        @RequestParam(name = "limit", required = false, value = "limit", defaultValue = "10") int limit,
                                                        @RequestParam(name = "query", required = false, value = "query") String query) {
        return postService.getPostsBySearch(offset, limit, query);
    }

    @GetMapping("/api/post/byDate")
    public ResponseEntity<PrePostsResponse> searchPostsByDate(@RequestParam(name = "offset", required = false, value = "offset", defaultValue = "0") int offset,
                                                              @RequestParam(name = "limit", required = false, value = "limit", defaultValue = "10") int limit,
                                                              @RequestParam(name = "date", required = false, value = "date") String date) throws ParseException {
        return postService.getPostsByDate(offset, limit, date);
    }

    @GetMapping("/api/post/byTag")
    public ResponseEntity<PrePostsResponse> searchPostsByTag(@RequestParam(name = "offset", required = false, value = "offset", defaultValue = "0") int offset,
                                                             @RequestParam(name = "limit", required = false, value = "limit", defaultValue = "10") int limit,
                                                             @RequestParam(name = "tag", required = false, value = "tag") String tag) {
        return postService.getPostsByTag(offset, limit, tag);
    }

    @GetMapping("/api/post/{id}")
    public ResponseEntity<FullPostResponse> searchPostById(@PathVariable("id") int id) {
        return postService.getPostsById(id);
    }
}