package com.hdv.magiccoffee.features.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.hdv.magiccoffee.MainActivity;
import com.hdv.magiccoffee.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.loginBtn.setOnClickListener(view -> loginViewModel.login());
        loginViewModel.getNavigate().observe(this, navigationDestination -> {
            if (navigationDestination == NavigationDestination.HOME) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}