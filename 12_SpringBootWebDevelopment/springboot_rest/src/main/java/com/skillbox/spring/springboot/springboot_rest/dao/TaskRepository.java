package com.skillbox.spring.springboot.springboot_rest.dao;

import com.skillbox.spring.springboot.springboot_rest.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}