package org.skillbox.springbootrest.repository;

import org.skillbox.springbootrest.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}