package com.taskmanagement.taskmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by georgekankava on 30.07.17.
 */
@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(String name, String description) {
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setAddedDate(new Date());
        task.setCompleted(false);
        taskRepository.save(task);
        return task;
    }

    public Task editTask(Integer id, String name, String description) {
        Task task = taskRepository.findOne(id);
        task.setName(name);
        task.setDescription(description);
        taskRepository.save(task);
        return task;
    }

    public Task completeTask(Integer id) {
        Task task = taskRepository.findOne(id);
        if (task.isCompleted()) {
            task.setCompletedDate(null);
            task.setCompleted(false);
        } else {
            task.setCompletedDate(new Date());
            task.setCompleted(true);
        }
        taskRepository.save(task);
        return task;
    }
    
    public void deleteTask(Integer id) {
        taskRepository.delete(id);
    }


    public Task getTask(Integer id) {
        return taskRepository.findOne(id);
        
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }
}
