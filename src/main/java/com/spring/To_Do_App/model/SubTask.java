package com.spring.To_Do_App.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="sub_tasks")
public class SubTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDate dueDate;
    private String status; // e.g. OPEN, DONE

    @ManyToOne
    @JoinColumn(name="parent_task_id", nullable = false)
    private Task parentTask;

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

    public Task getParentTask() {
        return parentTask;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    public SubTask(Long id, String title, LocalDate dueDate, String status, Task parentTask) {
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
        this.status = status;
        this.parentTask = parentTask;
    }

    public SubTask() {}

    // Constructors, getters, setters ...
}
