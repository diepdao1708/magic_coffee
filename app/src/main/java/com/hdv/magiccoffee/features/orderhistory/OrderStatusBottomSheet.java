package com.hdv.magiccoffee.features.orderhistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hdv.magiccoffee.databinding.BottomSheetOrderStatusBinding;

public class OrderStatusBottomSheet extends BottomSheetDialogFragment {
    BottomSheetOrderStatusBinding binding;

    public OrderStatusBottomSheet() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetOrderStatusBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}
