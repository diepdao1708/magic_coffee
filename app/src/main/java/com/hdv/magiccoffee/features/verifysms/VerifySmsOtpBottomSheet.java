package com.hdv.magiccoffee.features.verifysms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hdv.magiccoffee.databinding.BottomSheetVerifySmsOtpBinding;
import com.hdv.magiccoffee.features.login.LoginActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class VerifySmsOtpBottomSheet extends BottomSheetDialogFragment {

    BottomSheetVerifySmsOtpBinding binding;
    VerifySmsOtpViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetVerifySmsOtpBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(this).get(VerifySmsOtpViewModel.class);

        viewModel.getNavigate().observe(getViewLifecycleOwner(), navigationDestination -> {
            if (navigationDestination == NavigationDestination.HOME) {
                ((LoginActivity) requireActivity()).navigateToHome();
            }
        });

        binding.backBtn.setOnClickListener(view -> dismiss());
        binding.doneBtn.setOnClickListener(view -> viewModel.validateOTP(binding.enterOtpEdt.getText().toString().trim()));

        return binding.getRoot();
    }
}
