package com.hdv.magiccoffee.features.splash;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

enum NavigationDestination {
    HOME,
    LOGIN,
}

public class SplashViewModel extends ViewModel {

    private final MutableLiveData<NavigationDestination> _navigate = new MutableLiveData<>(null);

    public LiveData<NavigationDestination> getNavigate() {
        return _navigate;
    }

    public void checkLogin() {
        // TODO
        _navigate.setValue(NavigationDestination.HOME);
    }
}
