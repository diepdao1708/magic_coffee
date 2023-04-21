package com.hdv.magiccoffee.features.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.hdv.magiccoffee.data.models.SaveAccount;
import com.hdv.magiccoffee.data.models.auth.GoogleLoginRequest;
import com.hdv.magiccoffee.data.models.auth.LoginResponse;
import com.hdv.magiccoffee.data.models.auth.SmsRequest;
import com.hdv.magiccoffee.data.models.auth.SmsResponse;
import com.hdv.magiccoffee.data.repositories.AuthRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

enum NavigationDestination {
    VERIFY_OTP, HOME,
}

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private final AuthRepository authRepository;
    private final GoogleSignInClient googleSignInClient;
    private final SharedPreferences sharedPreferences;

    @Inject
    public LoginViewModel(GoogleSignInClient googleSignInClient, SharedPreferences sharedPreferences) {
        authRepository = new AuthRepository();
        this.googleSignInClient = googleSignInClient;
        this.sharedPreferences = sharedPreferences;
    }

    private final MutableLiveData<NavigationDestination> _navigate = new MutableLiveData<>();

    public LiveData<NavigationDestination> getNavigate() {
        return _navigate;
    }

    private final MutableLiveData<Intent> _intent = new MutableLiveData<>();

    public LiveData<Intent> getIntent() {
        return _intent;
    }

    private final MutableLiveData<String> _error = new MutableLiveData<>();

    public LiveData<String> getError() {
        return _error;
    }

    public void login(String phoneNumber) {
        phoneNumber = "+84" + phoneNumber.substring(1);
        authRepository.loginWithPhoneNumber(new SmsRequest(phoneNumber))
                .subscribe(new SingleObserver<SmsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SmsResponse smsResponse) {
                        SaveAccount.phoneNumber = smsResponse.getPhoneNumber();
                        _navigate.setValue(NavigationDestination.VERIFY_OTP);
                    }

                    @Override
                    public void onError(Throwable e) {
                        _error.postValue(e.getMessage());
                    }
                });
    }

    public void signInWithGoogle() {
        _intent.postValue(googleSignInClient.getSignInIntent());
    }

    public void handleLoginResult(Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            String googleToken = task.getResult(ApiException.class).getIdToken();
            if (googleToken == null) {
                _error.postValue("Khong lay duoc account");
            }
            loginWithGoogle(googleToken);
        } catch (ApiException e) {
            _error.postValue(e.getMessage());
        }
    }

    private void loginWithGoogle(String googleToken) {
        Log.d("TAG", googleToken);
        authRepository.loginWithGoogle(new GoogleLoginRequest(googleToken))
                .subscribe(new SingleObserver<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(LoginResponse loginResponse) {
                        SaveAccount.accessToken = loginResponse.getAccessToken();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("ACCESS_TOKEN", loginResponse.getAccessToken());
                        editor.apply();
                        _navigate.setValue(NavigationDestination.HOME);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
