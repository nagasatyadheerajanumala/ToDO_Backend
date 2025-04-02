package com.spring.To_Do_App.model;
import jakarta.persistence.*;

@Entity
@Table(name="task_attributes")
public class TaskAttribute {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;   // e.g. "priority", "color", etc.
    private String value;  // e.g. "high", "#FF0000"

    @ManyToOne
    @JoinColumn(name="task_id", nullable = false)
    private Task task;

    public TaskAttribute() {}

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public TaskAttribute(Long id, String name, String value, Task task) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.task = task;
    }
// Constructors, getters, setters ...
}
