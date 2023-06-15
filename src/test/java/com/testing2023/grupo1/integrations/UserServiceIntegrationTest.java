package com.testing2023.grupo1.integrations;

import com.testing2023.grupo1.controller.UserController;
import com.testing2023.grupo1.controller.requests.user.LoginRequest;
import com.testing2023.grupo1.controller.requests.user.NewUserRequest;
import com.testing2023.grupo1.controller.responses.user.LoginResponse;
import com.testing2023.grupo1.controller.responses.user.SigninResponse;
import com.testing2023.grupo1.entity.User;
import com.testing2023.grupo1.repository.TaskRepository;
import com.testing2023.grupo1.repository.UserRepository;
import com.testing2023.grupo1.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserServiceIntegrationTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserController userController;

    @Test
    @DisplayName("Usuario se registra correctamente")
    public void createUser() {
        // Arrange
        String username = "Testuser222";
        String password = "Testuser222";
        NewUserRequest newUserRequest = new NewUserRequest(username, password);

        // Act
        SigninResponse response = userController.signin(newUserRequest);

        // Assert
        Assertions.assertNotNull(response);
        Assertions.assertEquals(username, response.getUsername());
    }

    @Test
    @DisplayName("Usuario se logea correctamente")
    public void login() {
        // Arrange
        String username = "Testuser111";
        String password = "Testuser111";
        LoginRequest loginRequest = new LoginRequest(username, password);

        User user = new User(username, password);
        userRepository.save(user);

        // Act
        LoginResponse response = userController.login(loginRequest);

        // Assert
        Assertions.assertNotNull(response);
        Assertions.assertEquals(username, response.getUsername());
    }
}
