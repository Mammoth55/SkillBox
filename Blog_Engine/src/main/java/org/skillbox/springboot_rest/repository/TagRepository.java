package org.skillbox.springboot_rest.repository;

import org.skillbox.springboot_rest.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
}