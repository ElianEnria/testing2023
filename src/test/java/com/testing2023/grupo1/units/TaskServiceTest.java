package com.testing2023.grupo1.units;

import com.testing2023.grupo1.controller.requests.task.NewTaskRequest;
import com.testing2023.grupo1.controller.responses.user.TaskResponse;
import com.testing2023.grupo1.entity.Task;
import com.testing2023.grupo1.entity.User;
import com.testing2023.grupo1.repository.TaskRepository;
import com.testing2023.grupo1.service.TaskService;
import com.testing2023.grupo1.service.UserService;
import com.testing2023.grupo1.util.DataMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private TaskService taskService;

    @Test
    @DisplayName("Se obtienen todas las tareas correctamente")
    public void getAllTasks() {
        // Sujeto de prueba
        List<Task> tasks = DataMock.buildTasks();
        List<TaskResponse> expectedResponse = DataMock.buildTaskResponseList(tasks);

        Mockito.when(taskRepository.findAll()).thenReturn(tasks);

        // Actuar
        List<TaskResponse> response = taskService.getAllTasks();

        // Afirmar
        Assertions.assertEquals(expectedResponse, response);
        Mockito.verify(taskRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Se crea una tarea correctamente")
    public void createTask() {
        // Sujeto de prueba
        NewTaskRequest request = DataMock.buildNewTaskRequest();

        User user = new User();
        user.setId(1L);
        user.setUsername("TestUser");

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle(request.getTitle());
        savedTask.setDateTime(request.getDateTime());
        savedTask.setDescription(request.getDescription());
        savedTask.setIsDone(false);
        savedTask.setUser(user);

        Mockito.when(userService.getUserById(request.getLoggedUserId())).thenReturn(user);
        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(savedTask);

        TaskResponse expectedResponse = new TaskResponse(
                savedTask.getId(),
                savedTask.getTitle(),
                savedTask.getDateTime(),
                savedTask.getDescription(),
                savedTask.getIsDone()
        );

        // Actuar
        TaskResponse response = taskService.create(request);

        // Afirmar
        Assertions.assertEquals(expectedResponse, response);
        Mockito.verify(userService, Mockito.times(1)).getUserById(request.getLoggedUserId());
        Mockito.verify(taskRepository, Mockito.times(1)).save(Mockito.any(Task.class));
    }
}

