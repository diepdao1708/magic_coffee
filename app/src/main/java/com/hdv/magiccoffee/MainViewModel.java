package com.hdv.magiccoffee;

import androidx.lifecycle.ViewModel;

import com.hdv.magiccoffee.models.SaveAccount;
import com.hdv.magiccoffee.models.User;
import com.hdv.magiccoffee.data.repositories.UserRepository;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class MainViewModel extends ViewModel {

    private final UserRepository userRepository;

    public MainViewModel() {
        userRepository = new UserRepository();
    }

    public void getCurrentUser() {
        userRepository.getCurrentUser()
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // noop
                    }

                    @Override
                    public void onSuccess(User user) {
                        String[] name = user.getFullname().split(" ");
                        String firstName = name[name.length - 1];
                        StringBuilder lastName = new StringBuilder();
                        for (int i = 0; i < name.length - 1; i++) {
                            lastName.append(name[i]).append(" ");
                        }
                        SaveAccount.id = user.getId();
                        SaveAccount.image = user.getAvatarLink();
                        SaveAccount.firstName = firstName;
                        SaveAccount.lastName = lastName.toString();
                        SaveAccount.email = user.getEmail();
                        SaveAccount.phoneNumber = user.getPhoneNum();
                    }

                    @Override
                    public void onError(Throwable e) {
                        // noop
                    }
                });
    }
}
