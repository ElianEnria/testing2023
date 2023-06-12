package com.testing2023.grupo1.units;

import com.testing2023.grupo1.entity.Task;
import com.testing2023.grupo1.service.TaskService;
import com.testing2023.grupo1.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskServiceTest {
    @Mock
    TaskService taskRepository;
    @Mock
    UserService userService;
    @InjectMocks
    TaskService taskService;

    @Test()
    @DisplayName("Se obtienen todas las tareas correctamente")
    public void getAllTasks(){
        // Sujeto de prueba
        List<Task> tasks = buildTasks();
        List<TaskResponse> expectedResponse = buildTaskResponseList(tasks);

        Mockito.when(taskRepository.findAll()).thenReturn(tasks);

        // Actuar
        List<TaskResponse> response = taskService.getAllTasks();

        // Afirmar
        Assertions.assertEquals(expectedResponse, response);
        Mockito.verify(taskRepository, Mockito.times(1)).findAll();
    }
    @Test
    @DisplayName("Se crea una tarea correctamente")
    public void createTask(){

    }

}
