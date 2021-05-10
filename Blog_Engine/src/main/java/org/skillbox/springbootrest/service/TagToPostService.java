package org.skillbox.springbootrest.service;

import org.skillbox.springbootrest.repository.TagToPostRepository;
import org.springframework.stereotype.Service;

@Service
public class TagToPostService {

    private final TagToPostRepository tagToPostRepository;

    public TagToPostService(TagToPostRepository tagToPostRepository) {
        this.tagToPostRepository = tagToPostRepository;
    }


}