package com.spring.To_Do_App.controller;

import com.spring.To_Do_App.model.Task;
import com.spring.To_Do_App.model.User;
import com.spring.To_Do_App.service.TaskService;
import com.spring.To_Do_App.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    // Create a task; instead of passing a userId, the owner is the authenticated user.
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestParam Long projectId, @RequestBody Task task) {
        // Retrieve the authenticated user's username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        // Create the task using the projectId and authenticated user (as the assigned user)
        Task createdTask = taskService.createTask(projectId, user.getId(), task);
        return ResponseEntity.ok(createdTask);
    }

    // Retrieve tasks by project
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Task>> getTasksByProject(@PathVariable Long projectId) {
        List<Task> tasks = taskService.getTasksByProject(projectId);
        return ResponseEntity.ok(tasks);
    }

    // Update a task
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        Task task = taskService.updateTask(taskId, updatedTask);
        return ResponseEntity.ok(task);
    }

    // Delete a task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}