package org.skillbox.springbootrest.service;

import org.skillbox.springbootrest.repository.PostCommentRepository;
import org.springframework.stereotype.Service;

@Service
public class PostCommentService {

    private final PostCommentRepository postCommentRepository;

    public PostCommentService(PostCommentRepository postCommentRepository) {
        this.postCommentRepository = postCommentRepository;
    }


}