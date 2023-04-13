package com.hdv.magiccoffee.features.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hdv.magiccoffee.R;
import com.hdv.magiccoffee.databinding.BottomSheetProductBinding;
import com.squareup.picasso.Picasso;

public class ProductBottomSheet extends BottomSheetDialogFragment {

    BottomSheetProductBinding binding;
    ProductViewModel productViewModel;

    public ProductBottomSheet() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetProductBinding.inflate(inflater, container, false);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getProduct(getArguments());

        productViewModel.getUiState().observe(getViewLifecycleOwner(), product -> {
            Picasso.get()
                    .load(product.getImage())
                    .placeholder(R.drawable.img_background_login)
                    .fit()
                    .into(binding.drinkImage);
            binding.drinkNameTxt.setText(product.getName());
            binding.drinkCostTxt.setText(String.valueOf(product.getCost()));
            binding.drinkDescriptionTxt.setText(product.getDescription());
        });

        binding.closeBtn.setOnClickListener(view -> dismiss());

        return binding.getRoot();
    }
}
