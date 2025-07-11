package com.ved.taskgram.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ved.taskgram.entity.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}