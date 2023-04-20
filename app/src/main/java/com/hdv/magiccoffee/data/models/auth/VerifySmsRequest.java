package com.hdv.magiccoffee.data.models.auth;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VerifySmsRequest implements Serializable {
    @SerializedName("phoneNum")
    String phoneNumber;
    String otp;

    public VerifySmsRequest(String phoneNumber, String otp) {
        this.phoneNumber = phoneNumber;
        this.otp = otp;
    }
}
