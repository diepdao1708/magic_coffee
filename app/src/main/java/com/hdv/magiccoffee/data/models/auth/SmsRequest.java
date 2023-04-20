package com.hdv.magiccoffee.data.models.auth;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SmsRequest implements Serializable {
    @SerializedName("phoneNum")
    String phoneNumber;

    public SmsRequest(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
