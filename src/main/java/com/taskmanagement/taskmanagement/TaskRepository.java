package com.taskmanagement.taskmanagement;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by georgekankava on 29.07.17.
 */
public interface TaskRepository extends JpaRepository<Task, Integer> {}
