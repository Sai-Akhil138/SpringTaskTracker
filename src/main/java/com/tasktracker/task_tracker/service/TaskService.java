package com.tasktracker.task_tracker.service;

import com.tasktracker.task_tracker.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAll();

    Task save(Task task);

    Task findById(Long id);

    void delete(Long id);
}
