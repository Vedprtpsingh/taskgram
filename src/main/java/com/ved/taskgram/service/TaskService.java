package com.ved.taskgram.service;

import java.util.List;

import com.ved.taskgram.dto.TaskDto;

public interface TaskService {
    List<TaskDto> getAllTasks();
    void createTask(TaskDto taskDto);
    void updateTask(TaskDto taskDto);
    void deleteTask(Long id);
    TaskDto getTaskById(Long id);
}
