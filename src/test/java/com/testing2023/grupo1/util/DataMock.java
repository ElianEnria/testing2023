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

        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Task 1");
        task1.setDateTime(LocalDateTime.now());
        task1.setDescription("Description 1");
        task1.setIsDone(false);
        tasks.add(task1);

        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Task 2");
        task2.setDateTime(LocalDateTime.now());
        task2.setDescription("Description 2");
        task2.setIsDone(false);
        tasks.add(task2);

        Task task3 = new Task();
        task3.setId(3L);
        task3.setTitle("Task 3");
        task3.setDateTime(LocalDateTime.now());
        task3.setDescription("Description 3");
        task3.setIsDone(false);
        tasks.add(task3);

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

}
