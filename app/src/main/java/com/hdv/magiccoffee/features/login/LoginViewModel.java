package com.hdv.magiccoffee.features.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

enum NavigationDestination {
    HOME,
}

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<NavigationDestination> _navigate = new MutableLiveData<>(null);

    public LiveData<NavigationDestination> getNavigate() {
        return _navigate;
    }

    public void login() {
        // TODO
        _navigate.setValue(NavigationDestination.HOME);
    }

}
