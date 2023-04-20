package com.hdv.magiccoffee.data.models.auth;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    @SerializedName("accessToken")
    String accessToken;
    String message;
    String status;

    public String getAccessToken() {
        return accessToken;
    }
}
