package com.hdv.magiccoffee.features.verifysms;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hdv.magiccoffee.data.models.SaveAccount;
import com.hdv.magiccoffee.data.models.auth.VerifySmsRequest;
import com.hdv.magiccoffee.data.models.auth.LoginResponse;
import com.hdv.magiccoffee.data.repositories.AuthRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

enum NavigationDestination {
    HOME,
}

@HiltViewModel
public class VerifySmsOtpViewModel extends ViewModel {

    private final SharedPreferences sharedPreferences;
    private final AuthRepository authRepository;

    @Inject
    public VerifySmsOtpViewModel(SharedPreferences sharedPreferences) {
        authRepository = new AuthRepository();
        this.sharedPreferences = sharedPreferences;
    }

    private final MutableLiveData<NavigationDestination> _navigate = new MutableLiveData<>(null);

    public LiveData<NavigationDestination> getNavigate() {
        return _navigate;
    }

    public void validateOTP(String otp) {
        authRepository.validateOTP(new VerifySmsRequest(SaveAccount.account.getPhoneNumber(), otp))
                .subscribe(new SingleObserver<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("CommitPrefEdits")
                    @Override
                    public void onSuccess(LoginResponse loginResponse) {
                        SaveAccount.accessToken = loginResponse.getAccessToken();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("ACCESS_TOKEN", loginResponse.getAccessToken());
                        editor.apply();
                        _navigate.postValue(NavigationDestination.HOME);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
