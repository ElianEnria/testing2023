package com.testing2023.grupo1.Controller;

import com.testing2023.grupo1.Entity.Task;
import com.testing2023.grupo1.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return tasks.stream().map(TaskDto::new).collect(Collectors.toList());
    }

    @PostMapping
    public TaskDto create(@RequestBody TaskDto taskDto) {
        Task task = taskDto.toTask();
        Task newTask = taskService.create(task);
        return new TaskDto(newTask);
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        Task task = taskDto.toTask();
        Task updatedTask = taskService.updateTask(id, task);
        return new TaskDto(updatedTask);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    public static class TaskDto {
        private Long id;
        private String title;
        private String description;

        public TaskDto() {}

        public TaskDto(Task task) {
            this.id = task.getId();
            this.title = task.getTitle();
            this.description = task.getDescription();
        }

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

        public Task toTask() {
            Task task = new Task();
            task.setId(id);
            task.setTitle(title);
            task.setDescription(description);
            return task;
        }
    }
}
