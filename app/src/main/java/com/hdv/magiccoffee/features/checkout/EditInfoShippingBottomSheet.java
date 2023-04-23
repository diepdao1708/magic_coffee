package com.hdv.magiccoffee.features.checkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hdv.magiccoffee.R;
import com.hdv.magiccoffee.databinding.BottomSheetEditInfoShippingBinding;
import com.hdv.magiccoffee.models.SaveCheckout;

public class EditInfoShippingBottomSheet extends BottomSheetDialogFragment {
    BottomSheetEditInfoShippingBinding binding;
    EditInfoShippingViewModel viewModel;

    public EditInfoShippingBottomSheet() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetEditInfoShippingBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(this).get(EditInfoShippingViewModel.class);

        binding.backBtn.setOnClickListener(view -> dismiss());
        if (SaveCheckout.address != null) {
            binding.enterAddressEdt.setText(SaveCheckout.address.getAddress());
        }
        if (SaveCheckout.name != null) {
            binding.enterNameEdt.setText(SaveCheckout.name);
        }
        if (SaveCheckout.phoneNumber != null) {
            binding.enterPhoneNumberEdt.setText(SaveCheckout.phoneNumber);
        }

        binding.updateBtn.setOnClickListener(view -> {
            SaveCheckout.name = binding.enterNameEdt.getText().toString().trim();
            SaveCheckout.phoneNumber = binding.enterPhoneNumberEdt.getText().toString().trim();
            viewModel.addAddress(binding.enterAddressEdt.getText().toString().trim());
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_editInfoShippingBottomSheet_to_checkoutBottomSheet);
            dismiss();
        });
        return binding.getRoot();
    }
}
