package org.skillbox.springboot_rest.repository;

import org.skillbox.springboot_rest.model.PostVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostVoteRepository extends JpaRepository<PostVote, Integer> {
}