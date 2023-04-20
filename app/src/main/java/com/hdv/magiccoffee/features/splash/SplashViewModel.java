package com.hdv.magiccoffee.features.splash;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.auth0.android.jwt.JWT;
import com.hdv.magiccoffee.data.models.SaveAccount;

import java.util.Date;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

enum NavigationDestination {
    HOME,
    LOGIN,
}

@HiltViewModel
public class SplashViewModel extends ViewModel {

    private final SharedPreferences sharedPreferences;

    @Inject
    public SplashViewModel(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    private final MutableLiveData<NavigationDestination> _navigate = new MutableLiveData<>(null);

    public LiveData<NavigationDestination> getNavigate() {
        return _navigate;
    }

    public void checkLogin() {
        String accessToken = sharedPreferences.getString("ACCESS_TOKEN", "null");
        if (accessToken.equals("null") || expiredAccessToken(accessToken)) {
            _navigate.setValue(NavigationDestination.LOGIN);
        } else {
            SaveAccount.accessToken = accessToken;
            _navigate.setValue(NavigationDestination.HOME);
        }
    }

    private Boolean expiredAccessToken(String accessToken) {
        JWT jwt = new JWT(accessToken);
        Date expireAt = jwt.getExpiresAt();
        if (expireAt == null) return false;
        return expireAt.before(new Date());
    }
}
