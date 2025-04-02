package com.spring.To_Do_App.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // The user who owns/created this project
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    // Collaborators
    @ManyToMany
    @JoinTable(
            name = "project_collaborators",
            joinColumns = @JoinColumn(name="project_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    private Set<User> collaborators = new HashSet<>();

    // Constructors, getters, setters ...

    public Project(Long id, String name, User owner, Set<User> collaborators) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.collaborators = collaborators;
    }

    public Project() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<User> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(Set<User> collaborators) {
        this.collaborators = collaborators;
    }
}
