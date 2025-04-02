package com.spring.To_Do_App.service;

import com.spring.To_Do_App.model.Project;
import com.spring.To_Do_App.model.Task;
import com.spring.To_Do_App.model.User;
import com.spring.To_Do_App.repository.ProjectRepository;
import com.spring.To_Do_App.repository.TaskRepository;
import com.spring.To_Do_App.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TaskService {

    private final TaskRepository taskRepo;
    private final ProjectRepository projectRepo;
    private final UserRepository userRepo;

    public TaskService(TaskRepository taskRepo,
                       ProjectRepository projectRepo,
                       UserRepository userRepo) {
        this.taskRepo = taskRepo;
        this.projectRepo = projectRepo;
        this.userRepo = userRepo;
    }

    /**
     * Creates a new task in a specified project.
     * Optionally assigns it to a user if userId is provided.
     */
    public Task createTask(Long projectId, Long userId, Task task) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
        User assignedTo = null;
        if (userId != null) {
            assignedTo = userRepo.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        }
        task.setProject(project);
        task.setAssignedTo(assignedTo);
        // Set default status if not provided
        if (task.getStatus() == null) {
            task.setStatus("OPEN");
        }
        return taskRepo.save(task);
    }

    /**
     * Retrieves a task by its ID.
     */
    public Task getTask(Long id) {
        return taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    /**
     * Retrieves all tasks for a given project.
     */
    public List<Task> getTasksByProject(Long projectId) {
        // For simplicity, fetching all tasks and filtering in code.
        // In production, consider creating a custom repository query.
        return taskRepo.findAll().stream()
                .filter(task -> task.getProject().getId().equals(projectId))
                .collect(Collectors.toList());
    }

    /**
     * Updates an existing task with new data.
     */
    public Task updateTask(Long taskId, Task updatedData) {
        Task existing = getTask(taskId);
        existing.setTitle(updatedData.getTitle());
        existing.setDescription(updatedData.getDescription());
        existing.setDueDate(updatedData.getDueDate());
        existing.setStatus(updatedData.getStatus());
        // Update additional fields as needed
        return taskRepo.save(existing);
    }

    /**
     * Deletes a task by its ID.
     */
    public void deleteTask(Long taskId) {
        taskRepo.deleteById(taskId);
    }
}