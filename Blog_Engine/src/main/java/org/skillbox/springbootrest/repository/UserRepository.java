package org.skillbox.springbootrest.repository;

import org.skillbox.springbootrest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}