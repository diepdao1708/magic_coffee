package com.hdv.magiccoffee.features.orderhistory;

import android.annotation.SuppressLint;
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
import com.hdv.magiccoffee.databinding.BottomSheetOrderStatusBinding;

public class OrderStatusBottomSheet extends BottomSheetDialogFragment {
    BottomSheetOrderStatusBinding binding;
    OrderStatusViewModel viewModel;

    OrderAdapter adapter;

    public OrderStatusBottomSheet() {
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetOrderStatusBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(OrderStatusViewModel.class);
        viewModel.getData(getArguments());
        adapter = new OrderAdapter();
        binding.productRecyclerView.setAdapter(adapter);
        binding.ratingBar.setEnabled(false);

        viewModel.getOrder().observe(getViewLifecycleOwner(), order -> {
            binding.statusTxt.setText(order.getStatus() + " - " + order.getTimeOrder());
            binding.nameTxt.setText(order.getReceiverName());
            binding.phoneTxt.setText(order.getReceiverPhone());
            binding.addressTxt.setText(order.getAddress());
            if (order.getOrderItems() != null) {
                adapter.reloadData(order.getOrderItems());
            }
            if (order.getVoucher() != null) {
                binding.voucherTxt.setVisibility(View.VISIBLE);
                binding.voucherTxt.setText(order.getVoucher().getVoucher().getName());
            } else {
                binding.voucherTxt.setVisibility(View.GONE);
            }
            if (order.getRating() > 0) {
                binding.ratingBar.setVisibility(View.VISIBLE);
                binding.rateBtn.setVisibility(View.GONE);
                binding.ratingBar.setRating(order.getRating());
            } else {
                binding.rateBtn.setVisibility(View.VISIBLE);
                binding.ratingBar.setVisibility(View.GONE);
            }
            binding.priceTxt.setText(String.format("Số tiền thanh tiền: %.3fđ", order.getTotal()));
        });

        binding.backBtn.setOnClickListener(view -> dismiss());

        binding.rateBtn.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("order", viewModel.getOrder().getValue());
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_orderStatusBottomSheet_to_orderReviewBottomSheet, bundle);
        });

        return binding.getRoot();
    }
}
