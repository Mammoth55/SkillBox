package org.skillbox.springboot_rest.repository;

import org.skillbox.springboot_rest.model.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Integer> {
}