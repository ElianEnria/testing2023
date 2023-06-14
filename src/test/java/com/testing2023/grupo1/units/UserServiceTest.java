package com.testing2023.grupo1.units;

import com.testing2023.grupo1.controller.requests.user.LoginRequest;
import com.testing2023.grupo1.controller.requests.user.NewUserRequest;
import com.testing2023.grupo1.controller.responses.user.LoginResponse;
import com.testing2023.grupo1.controller.responses.user.SigninResponse;
import com.testing2023.grupo1.entity.User;
import com.testing2023.grupo1.repository.TaskRepository;
import com.testing2023.grupo1.repository.UserRepository;
import com.testing2023.grupo1.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;

import static com.testing2023.grupo1.util.DataMock.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    TaskRepository taskRepository;
    @InjectMocks
    UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test()
    @DisplayName("Se obtienen todos los usuarios correctamente")
    public void getUsers() {

        List<LoginResponse> expectedResponse = buildLoginResponseList();
        LocalDateTime currentDateTime = LocalDateTime.now();

        Mockito.when(userRepository.findAll()).thenReturn(
                buildUsers()
        );
        Mockito.when(taskRepository.findByUserAndDateTimeAfter(
                buildUsers().get(0), currentDateTime)
        ).thenReturn(new ArrayList<>());

        List<LoginResponse> response = userService.getAllUsers();
        Assert.assertEquals(
                response,
                expectedResponse
        );

    }

    @Test
    @DisplayName("Se obtiene un usuario por ID correctamente")
    public void getUserById() {
        User expectedUser = buildUser();
        Long userId = null;

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(
                new User("Fedesan1234", "Fede12345")
        ));

        User response = userService.getUserById(userId);
        Assert.assertEquals(response, expectedUser);
    }

    @Test
    @DisplayName("Usuario se logea correctamente")
    public void login() {
        LoginResponse expectedResponse = buildLoginResponse();
        LoginRequest sendedRequest = buildLoginRequest();

        Mockito.when(userRepository.findByUsernameAndPassword(
                sendedRequest.getUsername(),
                sendedRequest.getPassword()
        )).thenReturn(Optional.of(new User(
                "Fedesan1234",
                "Fede12345"
        )));

        Mockito.when(taskRepository.findByUserAndDateTimeAfter(null, null)).thenReturn(null);
        LoginResponse response = userService.login(sendedRequest);
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    @DisplayName("Usario se registra")
    public void createUser() {
        // Sujeto de prueba Organizar
        String username = "TestDeUsuario";
        String password = "TestDeUsuario12345";
        Long userId = 1L;

        NewUserRequest newUserRequest = new NewUserRequest();
        newUserRequest.setUsername(username);
        newUserRequest.setPassword(password);

        User savedUser = new User();
        savedUser.setId(userId);
        savedUser.setUsername(username);
        savedUser.setPassword(password);

        Mockito.when(userRepository.existsByUsername(username))
                .thenReturn(false);
        Mockito.when(userRepository.save(Mockito.any(User.class)))
                .thenReturn(savedUser);

        SigninResponse expectedResponse = new SigninResponse(userId, username);

        // Actuar Prueba:  Se guarda el resultado en la variable response. Esto representa la acción de registrar un nuevo usuario.
        SigninResponse response = userService.createUser(newUserRequest);

        // Assert Afirmar
        Assertions.assertNotNull(response);
        Assertions.assertEquals(userId, response.getId());
        Assertions.assertEquals(username, response.getUsername());

        Mockito.verify(userRepository, Mockito.times(1)).existsByUsername(username);
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }
    @Test
    @DisplayName("Error al registrar usuario duplicado")
    public void createUserError() {
        // Organizar
        String username = "TestDeUsuario";
        String password = "TestDeUsuario12345";

        NewUserRequest newUserRequest = new NewUserRequest();
        newUserRequest.setUsername(username);
        newUserRequest.setPassword(password);

        Mockito.when(userRepository.existsByUsername(username)).thenReturn(true);

        // Actuar y Afirmar
        Assertions.assertThrows(RuntimeException.class, () -> userService.createUser(newUserRequest));

        Mockito.verify(userRepository, Mockito.times(1)).existsByUsername(username);
        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any(User.class));
    }
}