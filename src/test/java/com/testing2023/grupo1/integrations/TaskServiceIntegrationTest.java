package com.testing2023.grupo1.integrations;

import com.testing2023.grupo1.controller.requests.task.NewTaskRequest;
import com.testing2023.grupo1.entity.Task;
import com.testing2023.grupo1.repository.TaskRepository;
import com.testing2023.grupo1.repository.UserRepository;
import com.testing2023.grupo1.util.Mapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static com.testing2023.grupo1.util.DataMock.*;
import static com.testing2023.grupo1.util.Mapper.buildRequestToJson;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class TaskServiceIntegrationTest {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        taskRepository.saveAll(buildTasks());
        userRepository.save(buildUser());
    }

    @AfterEach
    public void cleanUp() {
        taskRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    @DisplayName("Se obtienen todas las tareas")
    public void getAllTasks() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
        ;
    }

    @Test
    @DisplayName("Se crea una tarea correctamente")
    public void createTask() throws Exception {
        NewTaskRequest request = buildNewTaskRequest();
        String jsonRequest = buildRequestToJson(request);

        mockMvc.perform(post("/tasks")
                .contentType(APPLICATION_JSON)
                .content(jsonRequest)
        ).andExpect(status().isOk());

    }

    @Test
    @DisplayName("Error al crear una tarea, ingresa mal la fecha y hora")
    public void createTaskWithDateError() throws Exception {
        NewTaskRequest request = buildNewTaskRequestWithDateError();
        String jsonRequest = buildRequestToJson(request);

        String expectedExceptionMessage = "400 BAD_REQUEST \""+"La fecha y hora de la tarea deben ser posteriores a la fecha y hora actual"+"\"";

        mockMvc.perform(post("/tasks")
                .contentType(APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertEquals(expectedExceptionMessage, result.getResolvedException().getMessage())
        );
    }

    @Test
    @DisplayName("Error al crear una tarea, no ingresa el titulo")
    public void createTaskWithoutTitle() throws Exception {
        NewTaskRequest request = buildNewTaskRequestWithoutTitle();
        String jsonRequest = buildRequestToJson(request);

        String expectedExceptionMessage = "400 BAD_REQUEST \""+"El título de la tarea es obligatorio"+"\"";

        mockMvc.perform(post("/tasks")
                        .contentType(APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertEquals(expectedExceptionMessage, result.getResolvedException().getMessage())
        );
    }

    @Test
    @DisplayName("Error al crear una tarea, no ingresa la descripcion")
    public void createTaskWithoutDescription() throws Exception {
        NewTaskRequest request = buildNewTaskRequestWithoutDescription();
        String jsonRequest = buildRequestToJson(request);

        String expectedExceptionMessage = "400 BAD_REQUEST \""+"La descripción de la tarea es obligatoria"+"\"";

        mockMvc.perform(post("/tasks")
                        .contentType(APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertEquals(expectedExceptionMessage, result.getResolvedException().getMessage())
        );
    }

}