package com.example.soapTest.service;


import com.example.soapTest.dto.User;

public class GetUserResponse {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}