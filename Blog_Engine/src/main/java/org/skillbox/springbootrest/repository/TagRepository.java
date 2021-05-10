package org.skillbox.springbootrest.repository;

import org.skillbox.springbootrest.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
}