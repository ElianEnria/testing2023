package com.testing2023.grupo1.integrations;

import com.testing2023.grupo1.controller.TaskController;
import com.testing2023.grupo1.controller.UserController;
import com.testing2023.grupo1.controller.responses.user.TaskResponse;
import com.testing2023.grupo1.entity.Task;
import com.testing2023.grupo1.entity.User;
import com.testing2023.grupo1.repository.TaskRepository;
import com.testing2023.grupo1.repository.UserRepository;
import com.testing2023.grupo1.service.TaskService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-test.properties")
public class TaskServiceIntegrationTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserController userController;

    @Autowired
    private TaskService taskService; // Mock del servicio TaskService

    @Autowired
    private TaskController taskController; // Agrega la inyección de dependencia para el controlador TaskController

    @BeforeEach
    public void setUp() {
        User user = userRepository.save(new User("Testuser111", "Testuser111"));
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Tarea 1", LocalDateTime.now().plusDays(1), "Descripcion 1", user));
        tasks.add(new Task("Tarea 2", LocalDateTime.now().plusDays(2), "Descripcion 2", user));
        tasks.add(new Task("Tarea 3", LocalDateTime.now().plusDays(3), "Descripcion 3", user));
        taskRepository.saveAll(tasks);
    }
    @AfterEach
    public void cleanUp() {
        taskRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    @DisplayName("Obtener todas las tareas")
    public void getAllTasks() {
        // Arrange
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Tarea 1", LocalDateTime.now().plusDays(1), "Descripcion 1", userRepository.findById(1L).get()));
        tasks.add(new Task("Tarea 2", LocalDateTime.now().plusDays(2), "Descripcion 2", userRepository.findById(1L).get()));
        tasks.add(new Task("Tarea 3", LocalDateTime.now().plusDays(3), "Descripcion 3", userRepository.findById(1L).get()));


        // Act
        List<TaskResponse> response = taskController.getAllTasks(); // Utiliza el controlador TaskController

        // Assert
        Assertions.assertNotNull(response);
        Assertions.assertEquals(3, response.size());
        Assertions.assertEquals("Tarea 1", response.get(0).getTitle());
        Assertions.assertEquals("Tarea 2", response.get(1).getTitle());
        Assertions.assertEquals("Tarea 3", response.get(2).getTitle());

        // Verify
    }
}