package com.hdv.magiccoffee.features.product;

import static com.hdv.magiccoffee.features.commondata.Size.LARGE;
import static com.hdv.magiccoffee.features.commondata.Size.MEDIUM;
import static com.hdv.magiccoffee.features.commondata.Size.SMALL;

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
import com.hdv.magiccoffee.databinding.BottomSheetProductBinding;
import com.hdv.magiccoffee.features.commondata.Size;
import com.hdv.magiccoffee.features.commondata.Topping;
import com.squareup.picasso.Picasso;

public class ProductBottomSheet extends BottomSheetDialogFragment {

    BottomSheetProductBinding binding;
    ProductViewModel productViewModel;

    public ProductBottomSheet() {
    }

    @SuppressLint({"NonConstantResourceId", "DefaultLocale"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetProductBinding.inflate(inflater, container, false);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getProduct(getArguments());

        productViewModel.getProduct().observe(getViewLifecycleOwner(), product -> {
            Picasso.get()
                    .load(product.getImage())
                    .placeholder(R.drawable.img_background_login)
                    .fit()
                    .into(binding.drinkImage);
            binding.drinkNameTxt.setText(product.getName());
            binding.drinkCostTxt.setText(String.format("%.3fđ", product.getCost()));
            binding.drinkDescriptionTxt.setText(product.getDescription());
            setCost(product.getCost());
            setSize(product.getSize());
            setTopping(product.getTopping());
        });

        productViewModel.getQuantity().observe(getViewLifecycleOwner(), quantity -> {
                    binding.quantityTxt.setText(String.valueOf(quantity));
                    if (quantity != null && quantity == 0) {
                        binding.addBtn.setText(getString(R.string.remove_product));
                    }
                }
        );

        productViewModel.getTotalPrice().observe(getViewLifecycleOwner(), price ->
                binding.addBtn.setText(String.format("%.3fđ", price))
        );

        binding.sizeRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.large_radio_btn:
                    productViewModel.onCheckedSize(LARGE);
                    break;
                case R.id.medium_radio_btn:
                    productViewModel.onCheckedSize(Size.MEDIUM);
                    break;
                case R.id.small_radio_btn:
                    productViewModel.onCheckedSize(Size.SMALL);
                    break;
            }
        });

        binding.toppingRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.topping1_radio_button:
                    productViewModel.onCheckedTopping(Topping.ONE);
                    break;
                case R.id.topping2_radio_button:
                    productViewModel.onCheckedTopping(Topping.TWO);
                    break;
                case R.id.topping3_radio_button:
                    productViewModel.onCheckedTopping(Topping.THREE);
                    break;

                case R.id.topping4_radio_button:
                    productViewModel.onCheckedTopping(Topping.FOUR);
                    break;

                case R.id.topping5_radio_button:
                    productViewModel.onCheckedTopping(Topping.FIVE);
                    break;

                case R.id.no_radio_button:
                    productViewModel.onCheckedTopping(Topping.NONE);
                    break;
            }
        });

        binding.increaseBtn.setOnClickListener(view -> productViewModel.onIncreaseQuantity());
        binding.decreaseBtn.setOnClickListener(view -> productViewModel.onDecreaseQuantity());
        binding.closeBtn.setOnClickListener(view -> dismiss());

        binding.addBtn.setOnClickListener(view -> {
            productViewModel.onUpdateCheckout();
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_productBottomSheet_to_checkoutBottomSheet);
            dismiss();
        });

        return binding.getRoot();
    }

    private void setSize(Size size) {
        switch (size) {
            case LARGE:
                binding.largeRadioBtn.setChecked(true);
                break;
            case MEDIUM:
                binding.mediumRadioBtn.setChecked(true);
                break;
            case SMALL:
                binding.smallRadioBtn.setChecked(true);
                break;
        }
    }

    private void setTopping(Topping topping) {
        switch (topping) {
            case ONE:
                binding.topping1RadioButton.setChecked(true);
                break;
            case TWO:
                binding.topping2RadioButton.setChecked(true);
                break;
            case THREE:
                binding.topping3RadioButton.setChecked(true);
                break;
            case FOUR:
                binding.topping4RadioButton.setChecked(true);
                break;
            case FIVE:
                binding.topping5RadioButton.setChecked(true);
                break;
            case NONE:
                binding.noRadioButton.setChecked(true);
                break;
        }
    }

    private void setCost(double cost) {
        binding.largeRadioBtn.setText(LARGE.getSize(cost));
        binding.mediumRadioBtn.setText(MEDIUM.getSize(cost));
        binding.smallRadioBtn.setText(SMALL.getSize(cost));
    }
}
