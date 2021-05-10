package org.skillbox.springbootrest.repository;

import org.skillbox.springbootrest.model.TagToPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagToPostRepository extends JpaRepository<TagToPost, Integer> {
}