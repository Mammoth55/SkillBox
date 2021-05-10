package org.skillbox.springbootrest.repository;

import org.skillbox.springbootrest.model.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Integer> {
}