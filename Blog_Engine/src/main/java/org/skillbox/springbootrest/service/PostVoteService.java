package org.skillbox.springbootrest.service;

import org.skillbox.springbootrest.repository.PostVoteRepository;
import org.springframework.stereotype.Service;

@Service
public class PostVoteService {

    private final PostVoteRepository postVoteRepository;

    public PostVoteService(PostVoteRepository postVoteRepository) {
        this.postVoteRepository = postVoteRepository;
    }


}