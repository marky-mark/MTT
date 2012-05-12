package com.mtt.service;

import com.mtt.domain.entity.Task;
import com.mtt.service.bean.CreateTaskRequest;

import java.util.List;

/**
 * Represent the functions of taskService
 */
public interface TaskService {

    /**
     * find a task by an id
     * @param id of the task
     * @return the task object
     */
    Task find(Long id);

    /**
     * Find all the tasks associated with the user
     * @param id of the user
     * @return List of tasks associated with the user
     */
    List<Task> findByUser(Long id);

    /**
     * Remove an Task given an id
     * @param id - id of Task
     * @return the Task deleted
     */
    Task delete(Long id);

    /**
     * Create a new task
     * @param createTaskRequest  bean to represent the data
     * @return the task
     */
    Task create(CreateTaskRequest createTaskRequest);
}
