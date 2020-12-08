package com.example.web.models;

public class LoginResponse {

    private boolean error;
    private String message;
    private String name;
    private String password;

    public LoginResponse(Boolean error, String message, String name, String password){
        this.error = error;
        this.message = message;
        this.name = name;
        this.password = password;
    }

    public boolean isError() {
        return error;
    }

    public String isMessage() {
        return message;
    }

    public String isname() {
        return name;
    }

    public String isPassword() {
        return password;
    }

}
