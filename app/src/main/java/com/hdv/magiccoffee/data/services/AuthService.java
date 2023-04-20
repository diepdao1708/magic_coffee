package com.hdv.magiccoffee.data.services;


import com.hdv.magiccoffee.data.models.auth.SmsRequest;
import com.hdv.magiccoffee.data.models.auth.SmsResponse;
import com.hdv.magiccoffee.data.models.auth.VerifySmsRequest;
import com.hdv.magiccoffee.data.models.auth.VerifySmsResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST("/login/phonenum/")
    Single<SmsResponse> loginWithPhoneNumber(@Body SmsRequest smsRequest);

    @POST("/login/")
    Single<VerifySmsResponse> validateOTP(@Body VerifySmsRequest verifySmsRequest);
}
