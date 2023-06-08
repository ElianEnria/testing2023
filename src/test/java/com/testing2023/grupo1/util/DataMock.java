package com.testing2023.grupo1.util;

import com.testing2023.grupo1.controller.requests.user.LoginRequest;
import com.testing2023.grupo1.controller.responses.user.LoginResponse;
import com.testing2023.grupo1.entity.User;

import java.util.ArrayList;
import java.util.List;

public class DataMock {

    public static List<LoginResponse> buildLoginResponseList(){
        List<LoginResponse> loginResponseList = new ArrayList<>();
        loginResponseList.add(
                new LoginResponse(null, "fedesan", new ArrayList<>())
        );
        loginResponseList.add(
                new LoginResponse(null, "elian", new ArrayList<>())
        );

        return loginResponseList;
    }

    public static List<User> buildUsers(){
        List<User> userList = new ArrayList<>();

        userList.add(
                new User("fedesan", "1234")
        );

        userList.add(
                new User("elian", "1234")
        );

        return userList;
    }

    public static User buildUser(){
        return new User(
                "fedesan",
                "1234"
        );
    }

    public static LoginResponse buildLoginResponse(){
        return new LoginResponse(
                null,
                "fedesan",
                new ArrayList<>()
        );
    }

    public static LoginRequest buildLoginRequest(){
        return new LoginRequest(
                "fedesan",
                "1234"
        );
    }

}
