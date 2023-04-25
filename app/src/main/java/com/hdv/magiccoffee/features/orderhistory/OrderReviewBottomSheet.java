package com.hdv.magiccoffee.features.orderhistory;

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
import com.hdv.magiccoffee.databinding.BottomSheetOrderReviewBinding;

public class OrderReviewBottomSheet extends BottomSheetDialogFragment {
    BottomSheetOrderReviewBinding binding;
    OrderReviewViewModel viewModel;

    public OrderReviewBottomSheet() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetOrderReviewBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(OrderReviewViewModel.class);
        viewModel.getCurrentOrder(getArguments());

        viewModel.getOrder().observe(getViewLifecycleOwner(), order -> {
            if (order != null) {
                binding.nameTxt.setText(order.getName());
                binding.dateTxt.setText(order.getTimeOrder());
            }
        });

        binding.backBtn.setOnClickListener(view -> dismiss());
        binding.doneBtn.setOnClickListener(view -> viewModel.ratingOrder((int) binding.ratingBar.getRating()));
        viewModel.getMessage().observe(getViewLifecycleOwner(), it -> {
            if (it.equals("Success")) {
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_orderReviewBottomSheet_to_orderHistoryFragment);
                dismiss();
            }
        });
        return binding.getRoot();
    }
}
