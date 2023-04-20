package com.hdv.magiccoffee.features.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hdv.magiccoffee.data.models.SaveAccount;
import com.hdv.magiccoffee.data.models.auth.SmsRequest;
import com.hdv.magiccoffee.data.models.auth.SmsResponse;
import com.hdv.magiccoffee.data.repositories.AuthRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

enum NavigationDestination {
    VERIFY_OTP,
}

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private final AuthRepository authRepository;

    @Inject
    public LoginViewModel() {
        authRepository = new AuthRepository();
    }

    private final MutableLiveData<NavigationDestination> _navigate = new MutableLiveData<>(null);

    public LiveData<NavigationDestination> getNavigate() {
        return _navigate;
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
                        SaveAccount.setPhoneNumber(smsResponse.getPhoneNumber());
                        _navigate.setValue(NavigationDestination.VERIFY_OTP);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

}
