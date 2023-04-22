package com.hdv.magiccoffee.features.other;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

enum NavigationDestination {
    LOGOUT,
}

@HiltViewModel
public class OtherViewModel extends ViewModel {
    private final SharedPreferences sharedPreferences;

    private final MutableLiveData<NavigationDestination> _navigate = new MutableLiveData<>();

    public LiveData<NavigationDestination> getNavigate() {
        return _navigate;
    }

    @Inject
    public OtherViewModel(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ACCESS_TOKEN", "null");
        editor.apply();
        _navigate.postValue(NavigationDestination.LOGOUT);
    }
}
