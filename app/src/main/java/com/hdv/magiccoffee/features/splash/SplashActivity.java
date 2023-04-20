package com.hdv.magiccoffee.features.splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.hdv.magiccoffee.MainActivity;
import com.hdv.magiccoffee.databinding.ActivitySplashBinding;
import com.hdv.magiccoffee.features.login.LoginActivity;

import dagger.hilt.android.AndroidEntryPoint;

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;
    SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        splashViewModel = new ViewModelProvider(this).get(SplashViewModel.class);
        splashViewModel.checkLogin();
        splashViewModel.getNavigate().observe(this, navigate -> {
            if (navigate == NavigationDestination.HOME) {
                navigateTo(MainActivity.class);
            } else if (navigate == NavigationDestination.LOGIN) {
                navigateTo(LoginActivity.class);
            }
        });
    }

    private <T> void navigateTo(Class<T> cls) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            Intent intent = new Intent(this, cls);
            startActivity(intent);
            finish();
        }, 3000);
    }
}