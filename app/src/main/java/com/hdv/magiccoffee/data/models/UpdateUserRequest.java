package com.hdv.magiccoffee.data.models;

public class UpdateUserRequest {
    String fullname;
    String email;
    String phoneNum;

    public UpdateUserRequest(String fullname, String email, String phoneNum) {
        this.fullname = fullname;
        this.email = email;
        this.phoneNum = phoneNum;
    }
}
