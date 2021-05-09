package org.skillbox.springboot_rest.repository;

import org.skillbox.springboot_rest.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}