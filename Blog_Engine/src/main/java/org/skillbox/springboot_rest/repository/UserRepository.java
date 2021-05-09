package org.skillbox.springboot_rest.repository;

import org.skillbox.springboot_rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}