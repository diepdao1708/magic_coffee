package com.hdv.magiccoffee.data.services;


import com.hdv.magiccoffee.data.models.auth.FacebookLoginResponse;
import com.hdv.magiccoffee.data.models.auth.GoogleLoginRequest;
import com.hdv.magiccoffee.data.models.auth.LoginResponse;
import com.hdv.magiccoffee.data.models.auth.SmsRequest;
import com.hdv.magiccoffee.data.models.auth.SmsResponse;
import com.hdv.magiccoffee.data.models.auth.VerifySmsRequest;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AuthService {

    @POST("/login/phonenum/")
    Single<SmsResponse> loginWithPhoneNumber(@Body SmsRequest smsRequest);

    @POST("/login/")
    Single<LoginResponse> validateOTP(@Body VerifySmsRequest verifySmsRequest);

    @POST("/login/google/")
    Single<LoginResponse> loginWithGoogle(@Body GoogleLoginRequest googleLoginRequest);

    @GET("/login/facebook/")
    Single<FacebookLoginResponse> loginWithFacebook();
}
