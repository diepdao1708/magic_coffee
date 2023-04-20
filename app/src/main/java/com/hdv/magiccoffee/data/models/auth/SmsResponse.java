package com.hdv.magiccoffee.data.models.auth;

import com.google.gson.annotations.SerializedName;

public class SmsResponse {
    @SerializedName("phoneNum")
    String phoneNumber;
    String otp;

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
