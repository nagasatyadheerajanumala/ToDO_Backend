package com.spring.To_Do_App.repository;

import com.spring.To_Do_App.model.TaskAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskAttributeRepository extends JpaRepository<TaskAttribute, Long> {
}