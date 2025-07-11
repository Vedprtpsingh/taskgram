package com.ved.taskgram.service.impl;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.ved.taskgram.dto.TaskDto;
import com.ved.taskgram.entity.Task;
import com.ved.taskgram.repository.TaskRepository;
import com.ved.taskgram.service.TaskService;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    private ModelMapper modelMapper;
    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map((task) -> modelMapper.map(task, TaskDto.class)).collect(Collectors.toList());
    }
    @Override
    public void createTask(TaskDto taskDto) {
        Task task = modelMapper.map(taskDto, Task.class);
        taskRepository.save(task);
    }
    @Override
    public void updateTask(TaskDto taskDto) {
        taskRepository.save(modelMapper.map(taskDto, Task.class));
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDto getTaskById(Long id) {
        Task task=taskRepository.findById(id).get();
        TaskDto taskDto=modelMapper.map(taskRepository.findById(id), TaskDto.class);
        return taskDto;
    }

}
