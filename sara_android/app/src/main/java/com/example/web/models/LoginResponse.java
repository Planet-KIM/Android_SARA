package com.example.web.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("body")
    private String name;
    @SerializedName("body")
    private String password;

    public String isname() {
        return name;
    }

    public String isPassword() {
        return password;
    }

}
