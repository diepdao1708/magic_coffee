package com.hdv.magiccoffee.features.voucher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hdv.magiccoffee.R;
import com.hdv.magiccoffee.databinding.BottomSheetVoucherBinding;
import com.squareup.picasso.Picasso;

public class VoucherBottomSheet extends BottomSheetDialogFragment {

    BottomSheetVoucherBinding binding;
    VoucherViewModel voucherViewModel;

    public VoucherBottomSheet() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetVoucherBinding.inflate(inflater, container, false);

        voucherViewModel = new ViewModelProvider(this).get(VoucherViewModel.class);
        voucherViewModel.getVoucher(getArguments());

        voucherViewModel.getVoucher().observe(getViewLifecycleOwner(), voucher -> {
            binding.nameVoucher.setText(voucher.getName());
            Picasso.get()
                    .load(voucher.getImage())
                    .placeholder(R.drawable.img_background_login)
                    .fit()
                    .into(binding.imageVoucher);
            binding.dateVoucher.setText(voucher.getDate());
            binding.descriptionVoucher.setText(voucher.getDescription());
        });

        binding.startOrderBtn.setOnClickListener(view -> dismiss());

        binding.closeBtn.setOnClickListener(view -> dismiss());

        return binding.getRoot();
    }
}
