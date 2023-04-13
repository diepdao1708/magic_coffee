package com.hdv.magiccoffee.features.checkout;

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
import com.hdv.magiccoffee.databinding.BottomSheetCheckoutBinding;

import java.util.Objects;

public class CheckoutBottomSheet extends BottomSheetDialogFragment implements CheckoutProductAdapter.OnClickListener {

    BottomSheetCheckoutBinding binding;
    CheckoutProductAdapter checkoutProductAdapter;
    CheckoutViewModel checkoutViewModel;

    public CheckoutBottomSheet() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetCheckoutBinding.inflate(inflater, container, false);
        checkoutViewModel = new ViewModelProvider(this).get(CheckoutViewModel.class);
        checkoutProductAdapter = new CheckoutProductAdapter(this);
        binding.productRecyclerView.setAdapter(checkoutProductAdapter);
        binding.productRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        checkoutViewModel.getUiState().observe(getViewLifecycleOwner(), uiState -> checkoutProductAdapter.reloadData(uiState.products));

        binding.closeBtn.setOnClickListener(view -> dismiss());

        return binding.getRoot();
    }

    @Override
    public void OnItemCheckoutClick(int position, View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("product", Objects.requireNonNull(checkoutViewModel.getUiState().getValue()).products.get(position));
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_checkoutBottomSheet_to_productBottomSheet, bundle);
    }
}
