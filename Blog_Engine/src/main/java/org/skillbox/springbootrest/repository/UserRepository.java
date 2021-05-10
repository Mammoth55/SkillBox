package org.skillbox.springbootrest.repository;

import org.skillbox.springbootrest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}