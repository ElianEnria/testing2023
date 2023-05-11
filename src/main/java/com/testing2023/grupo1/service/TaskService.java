package com.testing2023.grupo1.service;

import com.testing2023.grupo1.controller.requests.task.NewTaskRequest;
import com.testing2023.grupo1.controller.responses.user.TaskResponse;
import com.testing2023.grupo1.entity.Task;
import com.testing2023.grupo1.entity.User;
import com.testing2023.grupo1.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;

    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream().map(
                task -> buildTaskResponse(task)
        ).collect(Collectors.toList());
    }

    public TaskResponse create(NewTaskRequest request) {

        validateTask(request);

        User user = userService.getUserById(request.getLoggedUserId());
        Task task = taskRepository.save(new Task(
                request.getTitle(),
                request.getDateTime(),
                request.getDescription(),
                user
        ));

        return buildTaskResponse(task);
    }

    private static void validateTask(NewTaskRequest request) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (request.getDateTime().isBefore(currentDateTime)) {
            throw new RuntimeException("La fecha y hora de la tarea deben ser posteriores a la fecha y hora actual");
        } else if (request.getTitle() == null || request.getTitle().isEmpty()) {
            throw new RuntimeException("El título de la tarea es obligatorio");
        } else if (request.getDescription() == null || request.getDescription().isEmpty()) {
            throw new RuntimeException("La descripción de la tarea es obligatoria");
        }
    }

    private static TaskResponse buildTaskResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDateTime(),
                task.getDescription(),
                task.getIsDone()
        );
    }
}
