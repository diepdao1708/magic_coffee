package com.hdv.magiccoffee.features.checkout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hdv.magiccoffee.R;
import com.hdv.magiccoffee.databinding.BottomSheetSelectPaymentBinding;
import com.hdv.magiccoffee.models.MethodPayment;
import com.hdv.magiccoffee.models.SaveCheckout;

public class SelectPaymentBottomSheet extends BottomSheetDialogFragment {

    BottomSheetSelectPaymentBinding binding;

    public SelectPaymentBottomSheet() {
    }

    @SuppressLint("NonConstantResourceId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetSelectPaymentBinding.inflate(inflater, container, false);

        binding.backBtn.setOnClickListener(view -> dismiss());
        if (SaveCheckout.methodPayment == MethodPayment.MONEY) {
            binding.moneyRadioBtn.setChecked(true);
        } else {
            binding.paypalRadioBtn.setChecked(true);
        }
        binding.paymentRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.money_radio_btn:
                    SaveCheckout.methodPayment = MethodPayment.MONEY;
                    Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_selectPaymentBottomSheet_to_checkoutBottomSheet);
                    dismiss();
                    break;
                case R.id.paypal_radio_btn:
                    SaveCheckout.methodPayment = MethodPayment.PAYPAL;
                    Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_selectPaymentBottomSheet_to_checkoutBottomSheet);
                    dismiss();
                    break;
            }
        });

        return binding.getRoot();
    }
}
