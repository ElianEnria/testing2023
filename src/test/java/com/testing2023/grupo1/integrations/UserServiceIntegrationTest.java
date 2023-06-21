package com.testing2023.grupo1.integrations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.testing2023.grupo1.controller.requests.user.LoginRequest;
import com.testing2023.grupo1.controller.requests.user.NewUserRequest;
import com.testing2023.grupo1.controller.responses.user.LoginResponse;
import com.testing2023.grupo1.repository.UserRepository;
import com.testing2023.grupo1.util.Mapper;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static com.testing2023.grupo1.util.DataMock.*;
import static com.testing2023.grupo1.util.Mapper.buildRequestToJson;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class UserServiceIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        userRepository.save(
                buildUser()
        );
    }

    @AfterEach
    public void cleanMocks(){
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Usuario se registra correctamente")
    public void createUser() throws Exception {

        NewUserRequest request = buildSigninUserRequest();
        String requestJson = buildRequestToJson(request);

        mockMvc.perform(
                post("/users/signin")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson)
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$.id").value(2)
        ).andExpect(
                jsonPath("$.username").value("TestUser222")
        );
    }

    @Test
    @DisplayName("Usuario no se puede registrar debido a que el usuario ya existe y se lanza una excepcion")
    public void createUserWithError() throws Exception {
        NewUserRequest request = buildNewUserRequest();
        String requestJson = buildRequestToJson(request);

        String expectedExceptionMessage = "400 BAD_REQUEST \""+"El usuario ingresado ya está registrado"+"\"";


        mockMvc.perform(
                post("/users/signin")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson)
        ).andExpect(
                status().isBadRequest()
        ).andExpect(
                result -> Assertions.assertEquals(expectedExceptionMessage, result.getResolvedException().getMessage())
        );
    }

    @Test
    @DisplayName("Usuario se logea correctamente")
    public void login() throws Exception {
        // Arrange
        LoginRequest loginRequest = buildLoginRequest();
        String requestJson = buildRequestToJson(loginRequest);

        mockMvc.perform(
                post("/users/login")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson)
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$.id").value(1)
        ).andExpect(
                jsonPath("$.username").value("Fedesan1234")
        );
    }

    @Test
    @DisplayName("El usuario intenta logearse pero ingresa mal las credenciales")
    public void loginWithError() throws Exception {
        LoginRequest loginRequest = buildLoginRequestWithBadCredentials();
        String requestJson = buildRequestToJson(loginRequest);

        String expectedExceptionMessage = "404 NOT_FOUND \""+"El usuario o la contraseña son incorrectas, intente nuevamente."+"\"";

        mockMvc.perform(
                post("/users/login")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson)
        ).andExpect(
                status().isNotFound()
        ).andExpect(
                result -> Assertions.assertEquals(expectedExceptionMessage, result.getResolvedException().getMessage())
        );
    }

}
