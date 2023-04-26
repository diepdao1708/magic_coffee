package com.hdv.magiccoffee.data.models.auth;

public class FacebookLoginResponse {

    String data;
    int status;
    String message;

    public String getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
