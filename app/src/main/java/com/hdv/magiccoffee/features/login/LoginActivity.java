package com.hdv.magiccoffee.features.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.hdv.magiccoffee.MainActivity;
import com.hdv.magiccoffee.databinding.ActivityLoginBinding;
import com.hdv.magiccoffee.features.verifysms.VerifySmsOtpBottomSheet;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.loginBtn.setOnClickListener(view -> loginViewModel.login(binding.enterPhoneNumEdt.getText().toString().trim()));
        loginViewModel.getNavigate().observe(this, navigationDestination -> {
            if (navigationDestination == NavigationDestination.VERIFY_OTP) {
                (new VerifySmsOtpBottomSheet()).show(getSupportFragmentManager(), "verify sms otp");
            }
        });
    }

    public void navigateToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}