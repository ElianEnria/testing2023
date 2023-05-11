package com.testing2023.grupo1.controller;

import com.testing2023.grupo1.controller.requests.task.NewTaskRequest;
import com.testing2023.grupo1.controller.responses.user.TaskResponse;
import com.testing2023.grupo1.entity.Task;
import com.testing2023.grupo1.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public TaskResponse create(@RequestBody NewTaskRequest request) {
        return taskService.create(request);
    }

}
