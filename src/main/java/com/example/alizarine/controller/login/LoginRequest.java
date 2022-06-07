package com.example.alizarine.controller.login;

import javax.validation.constraints.NotNull;

public class LoginRequest {
    @NotNull
    private String email;

    @NotNull
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "email='" + email + '\'' +
                ", password=" + password +
                '}';
    }
}
