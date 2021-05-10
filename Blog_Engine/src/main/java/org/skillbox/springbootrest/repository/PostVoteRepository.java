package org.skillbox.springbootrest.repository;

import org.skillbox.springbootrest.model.PostVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostVoteRepository extends JpaRepository<PostVote, Integer> {
}