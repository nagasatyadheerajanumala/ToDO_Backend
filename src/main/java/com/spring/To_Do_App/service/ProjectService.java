package com.spring.To_Do_App.service;

import com.spring.To_Do_App.model.Project;
import com.spring.To_Do_App.model.User;
import com.spring.To_Do_App.repository.ProjectRepository;
import com.spring.To_Do_App.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ProjectService {

    private final ProjectRepository projectRepo;
    private final UserRepository userRepo;

    public ProjectService(ProjectRepository projectRepo, UserRepository userRepo) {
        this.projectRepo = projectRepo;
        this.userRepo = userRepo;
    }

    /**
     * Creates a new project with the specified owner and name.
     * The owner is automatically added as a collaborator.
     */
    public Project createProject(Long ownerId, String name) {
        User owner = userRepo.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + ownerId));
        Project project = new Project();
        project.setName(name);
        project.setOwner(owner);
        project.getCollaborators().add(owner); // Add owner as default collaborator
        return projectRepo.save(project);
    }

    /**
     * Adds collaborators to an existing project.
     *
     * @param projectId the ID of the project
     * @param userIds   the set of user IDs to be added as collaborators
     */
    public Project addCollaborators(Long projectId, Set<Long> userIds) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
        for (Long uid : userIds) {
            User user = userRepo.findById(uid)
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + uid));
            project.getCollaborators().add(user);
        }
        return projectRepo.save(project);
    }

    /**
     * Retrieves all projects.
     */
    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    public List<Project> getProjectsForUser(Long ownerId) {
        // Assuming your ProjectRepository has a custom method or you can filter here:
        return projectRepo.findAll().stream()
                .filter(p -> p.getOwner().getId().equals(ownerId))
                .collect(Collectors.toList());
    }

    public Project getProjectById(Long id) {
        return projectRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
    }
}