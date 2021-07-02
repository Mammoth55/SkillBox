package org.skillbox.springbootrest.repository;

import org.skillbox.springbootrest.model.Post;
import org.skillbox.springbootrest.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    @Query("select t from Tag t where t.name = ?1")
    Tag findByName(String name);
}