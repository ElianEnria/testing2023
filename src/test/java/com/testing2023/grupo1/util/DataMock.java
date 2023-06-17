package com.testing2023.grupo1.util;

import com.testing2023.grupo1.controller.requests.task.NewTaskRequest;
import com.testing2023.grupo1.controller.requests.user.LoginRequest;
import com.testing2023.grupo1.controller.requests.user.NewUserRequest;
import com.testing2023.grupo1.controller.responses.user.LoginResponse;
import com.testing2023.grupo1.controller.responses.user.SigninResponse;
import com.testing2023.grupo1.controller.responses.user.TaskResponse;
import com.testing2023.grupo1.entity.Task;
import com.testing2023.grupo1.entity.User;
import com.testing2023.grupo1.service.TaskService;

import java.time.LocalDateTime;
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

    public static LoginRequest buildLoginRequestWithBadCredentials(){
        return new LoginRequest(
                "Fedesan1234",
                "Fede123"
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
    public static List<Task> buildTasks() {
        List<Task> tasks = new ArrayList<>();

        tasks.add (new Task(
                "Task 1",
                LocalDateTime.now(),
                "Description 1"
        ));

        tasks.add (new Task(
                "Task 2",
                LocalDateTime.now(),
                "Description 2"
        ));

        tasks.add (new Task(
                "Task 3",
                LocalDateTime.now(),
                "Description 3"
        ));

        return tasks;
    }
    public static List<TaskResponse> buildTaskResponseList(List<Task> tasks) {
        List<TaskResponse> taskResponseList = new ArrayList<>();

        for (Task task : tasks) {
            TaskResponse taskResponse = new TaskResponse();
            taskResponse.setId(task.getId());
            taskResponse.setTitle(task.getTitle());
            taskResponse.setDateTime(task.getDateTime());
            taskResponse.setDescription(task.getDescription());
            taskResponse.setIsDone(task.getIsDone());

            taskResponseList.add(taskResponse);
        }

        return taskResponseList;
    }

    public static NewTaskRequest buildNewTaskRequest() {
        NewTaskRequest request = new NewTaskRequest();
        request.setTitle("Task Title");
        request.setDateTime(LocalDateTime.now().plusDays(1)); // Fecha y hora posterior a la actual
        request.setDescription("Task Description");
        request.setLoggedUserId(1L);
        return request;
    }

    public static NewTaskRequest buildNewTaskRequestWithDateError(){
        return new NewTaskRequest(
                "Task Title",
                LocalDateTime.now(),
                "Task description",
                1L
        );
    }

    public static NewTaskRequest buildNewTaskRequestWithoutTitle(){
        return new NewTaskRequest(
                "",
                LocalDateTime.now().plusDays(1),
                "Task description",
                1L
        );
    }

    public static NewTaskRequest buildNewTaskRequestWithoutDescription(){
        return new NewTaskRequest(
                "Task title",
                LocalDateTime.now().plusDays(1),
                "",
                1L
        );
    }

    public static NewUserRequest buildSigninUserRequest() {
        return new NewUserRequest("TestUser222", "TestUser222");
    }

}
