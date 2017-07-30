package com.taskmanagement.taskmanagement;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

/**
 * Created by georgekankava on 29.07.17.
 */
@RestController
public class TaskManagementController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public Task createTask(@RequestBody TaskForm taskForm) {
        return taskService.createTask(taskForm.getName(), taskForm.getDescription());
    }


    @PutMapping("/edit")
    public Task editTask(@RequestBody TaskForm taskForm) {
        return taskService.editTask(taskForm.getId(), taskForm.getName(), taskForm.getDescription());
    }

    @GetMapping("/completed/{taskId}")
    public Task completeTask(@PathVariable("taskId") Integer id) {
        return taskService.completeTask(id);
    }

    @DeleteMapping(value = "/delete/{taskId}")
    public List<Task> deleteTask(@PathVariable("taskId") Integer id) {
        taskService.deleteTask(id);
        return taskService.getTasks();
    }


    @GetMapping("/task")
    public Task getTask(@PathVariable("taskId") Integer id) {
        return taskService.getTask(id);
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @Getter
    @Setter
    public static class TaskForm {
        private Integer id;
        private String name;
        private String description;
    }
}
