package com.hdv.magiccoffee.features.checkout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hdv.magiccoffee.R;
import com.hdv.magiccoffee.data.models.SaveCheckout;
import com.hdv.magiccoffee.databinding.BottomSheetCheckoutBinding;
import com.hdv.magiccoffee.features.commondata.Product;
import com.hdv.magiccoffee.features.commondata.RedirectingData;

public class CheckoutBottomSheet extends BottomSheetDialogFragment implements CheckoutProductAdapter.OnClickListener {

    BottomSheetCheckoutBinding binding;
    CheckoutProductAdapter checkoutProductAdapter;
    CheckoutViewModel checkoutViewModel;

    public CheckoutBottomSheet() {
    }

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetCheckoutBinding.inflate(inflater, container, false);
        checkoutViewModel = new ViewModelProvider(this).get(CheckoutViewModel.class);
        checkoutProductAdapter = new CheckoutProductAdapter(this);
        binding.productRecyclerView.setAdapter(checkoutProductAdapter);
        binding.productRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.shipPriceTxt.setText(String.format("%.3fđ", SaveCheckout.getShippingPrice()));

        checkoutViewModel.getProduct().observe(getViewLifecycleOwner(), products -> {
                    checkoutProductAdapter.reloadData(products);
                    binding.checkoutBtn.setVisibility(products.size() == 0 ? View.INVISIBLE : View.VISIBLE);
                }
        );

        checkoutViewModel.getTotalPrice().observe(getViewLifecycleOwner(), totalPrice -> {
            binding.priceDrinkTxt.setText(String.format("%.3fđ", totalPrice));
            binding.totalPriceTxt.setText(String.format("%.3fđ", SaveCheckout.checkoutPrice()));
            binding.checkoutPriceTxt.setText(String.format("%.3fđ", SaveCheckout.checkoutPrice()));
        });


        binding.closeBtn.setOnClickListener(view -> dismiss());
        binding.addBtn.setOnClickListener(view -> dismiss());

        binding.address.setOnClickListener(view -> {
            // TODO
        });

        binding.personInfo.setOnClickListener(view -> {
            // TODO
        });

        binding.voucherTxt.setOnClickListener(view -> {
            // TODO
        });

        binding.paymentTxt.setOnClickListener(view -> {
            // TODO
        });

        binding.checkoutBtn.setOnClickListener(view -> {
            // TODO
        });

        return binding.getRoot();
    }

    @Override
    public void OnItemCheckoutClick(Product product, int position, View view) {
        Bundle bundle = new Bundle();
        RedirectingData redirectingData = new RedirectingData(product, "CHECKOUT_BOTTOM_SHEET", position);
        bundle.putSerializable("product", redirectingData);
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_checkoutBottomSheet_to_productBottomSheet, bundle);
    }

    @Override
    public void OnDeleteItem(int position) {
        checkoutViewModel.onDelete(position);
    }
}
