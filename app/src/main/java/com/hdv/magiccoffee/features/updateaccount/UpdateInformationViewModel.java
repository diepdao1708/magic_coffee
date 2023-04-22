package com.hdv.magiccoffee.features.updateaccount;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hdv.magiccoffee.models.Account;
import com.hdv.magiccoffee.data.models.CommonResponse;
import com.hdv.magiccoffee.models.SaveAccount;
import com.hdv.magiccoffee.data.repositories.UserRepository;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

enum NavigationDestination {
    BACK,
}

public class UpdateInformationViewModel extends ViewModel {

    private final UserRepository userRepository;

    public UpdateInformationViewModel() {
        userRepository = new UserRepository();
    }

    private final MutableLiveData<Account> _account = new MutableLiveData<>(new Account(
            SaveAccount.id,
            SaveAccount.image,
            SaveAccount.firstName,
            SaveAccount.lastName,
            SaveAccount.email,
            SaveAccount.phoneNumber,
            SaveAccount.address
    ));

    public LiveData<Account> getAccount() {
        return _account;
    }

    private final MutableLiveData<NavigationDestination> _navigate = new MutableLiveData<>();

    public LiveData<NavigationDestination> getNavigate() {
        return _navigate;
    }


    private final MutableLiveData<String> _message = new MutableLiveData<>();

    public LiveData<String> getMessage() {
        return _message;
    }

    public void updateAccount(String firstName, String lastName, String email, String phoneNumber) {
        if (!phoneNumber.isEmpty()) phoneNumber = "+84" + phoneNumber.substring(1);
        String finalPhoneNumber = phoneNumber;
        userRepository.updateUser(SaveAccount.id, firstName, lastName, email, phoneNumber)
                .subscribe(new SingleObserver<CommonResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(CommonResponse commonResponse) {
                        SaveAccount.firstName = firstName;
                        SaveAccount.lastName = lastName;
                        SaveAccount.email = email;
                        SaveAccount.phoneNumber = finalPhoneNumber;
                        _account.postValue(new Account(
                                SaveAccount.id,
                                SaveAccount.image,
                                SaveAccount.firstName,
                                SaveAccount.lastName,
                                SaveAccount.email,
                                SaveAccount.phoneNumber,
                                SaveAccount.address
                        ));
                        _message.postValue(commonResponse.getMessage());
                    }

                    @Override
                    public void onError(Throwable e) {
                        _message.postValue(e.getMessage());
                    }
                });

    }
}
