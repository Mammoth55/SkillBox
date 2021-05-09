package org.skillbox.springboot_rest.repository;

import org.skillbox.springboot_rest.model.TagToPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagToPostRepository extends JpaRepository<TagToPost, Integer> {
}