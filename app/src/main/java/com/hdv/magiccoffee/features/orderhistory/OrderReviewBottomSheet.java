package com.hdv.magiccoffee.features.orderhistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hdv.magiccoffee.databinding.BottomSheetOrderReviewBinding;

public class OrderReviewBottomSheet extends BottomSheetDialogFragment {
    BottomSheetOrderReviewBinding binding;

    public OrderReviewBottomSheet() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetOrderReviewBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}
