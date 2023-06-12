package com.testing2023.grupo1.util;

import com.testing2023.grupo1.controller.requests.user.LoginRequest;
import com.testing2023.grupo1.controller.requests.user.NewUserRequest;
import com.testing2023.grupo1.controller.responses.user.LoginResponse;
import com.testing2023.grupo1.controller.responses.user.SigninResponse;
import com.testing2023.grupo1.entity.Task;
import com.testing2023.grupo1.entity.User;

import java.util.ArrayList;
import java.util.List;

public class DataMock {

    public static List<LoginResponse> buildLoginResponseList(){
        List<LoginResponse> loginResponseList = new ArrayList<>();
        loginResponseList.add(
                new LoginResponse(null, "Fedesan1234", new ArrayList<>())
        );
        loginResponseList.add(
                new LoginResponse(null, "Elian1234", new ArrayList<>())
        );

        return loginResponseList;
    }

    public static List<User> buildUsers(){
        List<User> userList = new ArrayList<>();

        userList.add(
                new User("Fedesan1234", "Fede12345")
        );

        userList.add(
                new User("Elian1234", "Elian12345")
        );

        return userList;
    }

    public static User buildUser(){
        return new User(
                "Fedesan1234",
                "Fede12345"
        );
    }

    public static LoginResponse buildLoginResponse(){
        return new LoginResponse(
                null,
                "Fedesan1234",
                new ArrayList<>()
        );
    }

    public static LoginRequest buildLoginRequest(){
        return new LoginRequest(
                "Fedesan1234",
                "Fede12345"
        );
    }
    public static SigninResponse buildSigninResponse(){
        return new SigninResponse(
                null,
                "Fedesan1234"

        );
    }
    public static NewUserRequest buildNewUserRequest(){
        return new NewUserRequest(
                "Fedesan1234",
                "Fede12345"
        );
    }
//    esto seran los build para services
    private List<Task> buildTasks() {
    List<Task> tasks = new ArrayList<>();
    tasks.add(new Task(1L, "Task 1", LocalDateTime.now(), "Description 1", false));
    tasks.add(new Task(2L, "Task 2", LocalDateTime.now(), "Description 2", false));
    tasks.add(new Task(3L, "Task 3", LocalDateTime.now(), "Description 3", false));
    return tasks;
    }

}
