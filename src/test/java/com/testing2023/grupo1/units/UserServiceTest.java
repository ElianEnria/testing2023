package com.testing2023.grupo1.units;

import com.testing2023.grupo1.controller.requests.user.LoginRequest;
import com.testing2023.grupo1.controller.requests.user.NewUserRequest;
import com.testing2023.grupo1.controller.responses.user.LoginResponse;
import com.testing2023.grupo1.controller.responses.user.SigninResponse;
import com.testing2023.grupo1.entity.User;
import com.testing2023.grupo1.repository.TaskRepository;
import com.testing2023.grupo1.repository.UserRepository;
import com.testing2023.grupo1.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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

    @Test()
    @DisplayName("Se obtienen todos los usuarios correctamente")
    public void getUsers(){

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
    public void getUserById(){
        User expectedUser = buildUser();
        Long userId = null;

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(
                new User("fedesan", "1234")
        ));

        User response = userService.getUserById(userId);
        Assert.assertEquals(response, expectedUser);
    }

    @Test
    @DisplayName("Usuario se logea correctamente")
    public void login(){
        LoginResponse expectedResponse = buildLoginResponse();
        LoginRequest sendedRequest = buildLoginRequest();

        Mockito.when(userRepository.findByUsernameAndPassword(
                sendedRequest.getUsername(),
                sendedRequest.getPassword()
        )).thenReturn(Optional.of(new User(
                "fedesan",
                "1234"
        )));

        Mockito.when(taskRepository.findByUserAndDateTimeAfter(null, null)).thenReturn(null);
        LoginResponse response = userService.login(sendedRequest);
        Assert.assertEquals(response, expectedResponse);
    }
    @Test
    @DisplayName("Usario se loguea con contraseña erronea")

    public void createUser(){
        SigninResponse expectedResponse = buildSigninResponse();
        NewUserRequest sendedRequest = buildNewUserRequest();

    }}
