package com.hdv.magiccoffee.data.repositories;

import com.hdv.magiccoffee.data.models.auth.GoogleLoginRequest;
import com.hdv.magiccoffee.data.models.auth.LoginResponse;
import com.hdv.magiccoffee.data.models.auth.SmsRequest;
import com.hdv.magiccoffee.data.models.auth.SmsResponse;
import com.hdv.magiccoffee.data.models.auth.VerifySmsRequest;
import com.hdv.magiccoffee.data.services.ApiService;
import com.hdv.magiccoffee.data.services.AuthService;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class AuthRepository {
    private final AuthService authService;

    public AuthRepository() {
        authService = ApiService.getAuthService();
    }

    public Single<SmsResponse> loginWithPhoneNumber(SmsRequest smsRequest) {
        return authService.loginWithPhoneNumber(smsRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<LoginResponse> validateOTP(VerifySmsRequest verifySmsRequest) {
        return authService.validateOTP(verifySmsRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<LoginResponse> loginWithGoogle(GoogleLoginRequest googleLoginRequest) {
        return authService.loginWithGoogle(googleLoginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
