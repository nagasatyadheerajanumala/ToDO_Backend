package com.spring.To_Do_App.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDate dueDate;
    private String status; // e.g. OPEN, IN_PROGRESS, DONE

    @ManyToOne
    @JoinColumn(name="project_id", nullable = false)
    private Project project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public List<TaskAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<TaskAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public Task(Long id, String title, String description, LocalDate dueDate, String status, Project project, User assignedTo, List<TaskAttribute> attributes, List<SubTask> subTasks) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.project = project;
        this.assignedTo = assignedTo;
        this.attributes = attributes;
        this.subTasks = subTasks;
    }

    // The user assigned to complete this task
    @ManyToOne
    @JoinColumn(name="assigned_to")
    private User assignedTo;

    @OneToMany(mappedBy="task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskAttribute> attributes = new ArrayList<>();

    @OneToMany(mappedBy="parentTask", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubTask> subTasks = new ArrayList<>();

    public Task() {}

    // Constructors, getters, setters ...
}
