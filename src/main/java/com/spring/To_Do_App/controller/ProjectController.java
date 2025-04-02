package com.spring.To_Do_App.controller;


import com.spring.To_Do_App.model.Project;
import com.spring.To_Do_App.model.User;
import com.spring.To_Do_App.service.ProjectService;
import com.spring.To_Do_App.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    // Create a project using the currently authenticated user as the owner.
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestParam String name) {
        // Retrieve the authenticated user's username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // Lookup the user in the database
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        // Create the project with the authenticated user as owner
        Project project = projectService.createProject(user.getId(), name);
        return ResponseEntity.ok(project);
    }

    // Retrieve projects for the currently authenticated user
    @GetMapping
    public ResponseEntity<List<Project>> getProjectsForCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        // Assuming you add a method in ProjectService to fetch projects by user ID.
        List<Project> projects = projectService.getProjectsForUser(user.getId());
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }
}