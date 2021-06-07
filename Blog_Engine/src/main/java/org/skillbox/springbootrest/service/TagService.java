package org.skillbox.springbootrest.service;

import org.skillbox.springbootrest.api.response.TagsResponse;
import org.skillbox.springbootrest.model.Tag;
import org.skillbox.springbootrest.model.TagWeight;
import org.skillbox.springbootrest.repository.PostRepository;
import org.skillbox.springbootrest.repository.TagRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private final PostRepository postRepository;

    public TagService(TagRepository tagRepository, PostRepository postRepository) {
        this.tagRepository = tagRepository;
        this.postRepository = postRepository;
    }

    public ResponseEntity<TagsResponse> getTags(String query) {
        Set<Tag> tags = new TreeSet<>(tagRepository.findAll());
        Tag mostFrequentTag = tags.stream().findFirst().orElseThrow();
        int totalPosts = postRepository.findAll().size();
        double normalK = totalPosts / (double) mostFrequentTag.getPosts().size();
        if (query != null) {
            tags = tags.stream().filter(tag -> tag.getName().contains(query.trim().toUpperCase())).collect(Collectors.toSet());
        }
        TagsResponse response = new TagsResponse();
        for (Tag tag : tags) {
            double currentWeight = normalK * tag.getPosts().size() / totalPosts;
            response.getTags().add(new TagWeight(tag.getName(), currentWeight));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}