package com.hdv.magiccoffee.features.checkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hdv.magiccoffee.R;
import com.hdv.magiccoffee.databinding.BottomSheetEditInfoShippingBinding;
import com.hdv.magiccoffee.models.Address;
import com.hdv.magiccoffee.models.SaveCheckout;

public class EditInfoShippingBottomSheet extends BottomSheetDialogFragment {
    BottomSheetEditInfoShippingBinding binding;

    public EditInfoShippingBottomSheet() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetEditInfoShippingBinding.inflate(inflater, container, false);

        binding.backBtn.setOnClickListener(view -> dismiss());
        binding.enterNameEdt.setText(SaveCheckout.name);
        binding.enterAddressEdt.setText(SaveCheckout.address.getAddress());
        binding.enterPhoneNumberEdt.setText(SaveCheckout.phoneNumber);

        binding.updateBtn.setOnClickListener(view -> {
            SaveCheckout.name = binding.enterNameEdt.getText().toString().trim();
            SaveCheckout.phoneNumber = binding.enterPhoneNumberEdt.getText().toString().trim();
            SaveCheckout.address =  new Address(binding.enterAddressEdt.getText().toString().trim());
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_editInfoShippingBottomSheet_to_checkoutBottomSheet);
            dismiss();
        });
        return binding.getRoot();
    }
}
