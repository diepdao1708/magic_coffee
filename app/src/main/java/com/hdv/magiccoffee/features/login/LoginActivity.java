package com.hdv.magiccoffee.features.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        Intent data = result.getData();
        loginViewModel.handleLoginResult(data);
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.loginBtn.setOnClickListener(view -> {
            String phone = binding.enterPhoneNumEdt.getText().toString().trim();
            if (phone.isEmpty()) {
                Toast.makeText(this, "Chua nhap so dien thoai", Toast.LENGTH_SHORT).show();
            } else {
                loginViewModel.login(phone);
            }
        });
        loginViewModel.getNavigate().observe(this, navigationDestination -> {
            if (navigationDestination == NavigationDestination.VERIFY_OTP) {
                (new VerifySmsOtpBottomSheet()).show(getSupportFragmentManager(), "verify sms otp");
            } else if (navigationDestination == NavigationDestination.HOME) {
                navigateToHome();
            }
        });

        loginViewModel.getError().observe(this, error -> Toast.makeText(this, error, Toast.LENGTH_SHORT).show());

        loginViewModel.getIntent().observe(this, launcher::launch);

        binding.signInWithFbBtn.setOnClickListener(view -> loginViewModel.loginWithFacebook());
        loginViewModel.getUrl().observe(this, it -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(it));
            startActivity(intent);
        });

        binding.signInWithGgBtn.setOnClickListener(view -> loginViewModel.signInWithGoogle());
    }

    public void navigateToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}