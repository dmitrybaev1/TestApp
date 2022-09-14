package com.example.testapp;

import com.example.testapp.api.response.UserApiResponse;
import com.example.testapp.entities.User;

public class UserMapper {

    public User toUser(UserApiResponse userApiResponse){
        return new User(
                userApiResponse.getFullName(),
                userApiResponse.getPosition(),
                userApiResponse.getWorkHoursInMonth(),
                userApiResponse.getWorkedOutHours()
        );
    }
}
