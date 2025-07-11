package com.ved.taskgram.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ved.taskgram.dto.TaskDto;
import com.ved.taskgram.service.TaskService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;
    @GetMapping("/tasks")
    public String getAllTasks(Model model){
        List<TaskDto>tasks=taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks/taskList";
    }
    @PostMapping("/tasks")
    public String SaveTask(@ModelAttribute("task") TaskDto taskDto,Model model){
        //save logic
        taskService.createTask(taskDto);
        return "redirect:/tasks?success";
    }
    @GetMapping("/createTask")
    public String CreateTaskForm(Model model){
        TaskDto taskDto=new TaskDto();
        model.addAttribute("task", taskDto);
        return "tasks/createTask";
    }
    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id){
        taskService.deleteTask(id);
        return "redirect:/tasks?deleted";
    }
    @GetMapping("/tasks/edit/{id}")
    public String editTask(@PathVariable("id") Long id,Model model){
        TaskDto taskDto=taskService.getTaskById(id);
        model.addAttribute("task", taskDto);
        return "tasks/editTask";
    }
    @PostMapping("/tasks/{id}")
    public String updateTask(@PathVariable("id") Long id,@ModelAttribute("task") TaskDto taskDto){
        taskDto.setId(id);
        taskService.updateTask(taskDto);
        return "redirect:/tasks?updated";
    }
}