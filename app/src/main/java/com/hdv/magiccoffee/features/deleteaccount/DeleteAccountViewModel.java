package com.hdv.magiccoffee.features.deleteaccount;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hdv.magiccoffee.data.models.CommonResponse;
import com.hdv.magiccoffee.data.models.SaveAccount;
import com.hdv.magiccoffee.data.repositories.UserRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

enum NavigationDestination {
    BACK,
}

@HiltViewModel
public class DeleteAccountViewModel extends ViewModel {
    private final UserRepository userRepository;

    private final MutableLiveData<NavigationDestination> _navigate = new MutableLiveData<>();

    public LiveData<NavigationDestination> getNavigate() {
        return _navigate;
    }

    private final MutableLiveData<String> _message = new MutableLiveData<>();

    public LiveData<String> getMessage() {
        return _message;
    }

    private final SharedPreferences sharedPreferences;

    @Inject
    public DeleteAccountViewModel(SharedPreferences sharedPreferences) {
        userRepository = new UserRepository();
        this.sharedPreferences = sharedPreferences;
    }

    public void deleteAccount() {
        userRepository.deleteUser(SaveAccount.id)

                .subscribe(new SingleObserver<CommonResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // noop
                    }

                    @Override
                    public void onSuccess(CommonResponse commonResponse) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("ACCESS_TOKEN", "null");
                        editor.apply();
                        _message.postValue(commonResponse.getMessage());
                        _navigate.postValue(NavigationDestination.BACK);
                    }

                    @Override
                    public void onError(Throwable e) {
                        _message.postValue(e.getMessage());
                    }
                });
    }
}
