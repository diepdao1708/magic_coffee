package com.hdv.magiccoffee.features.checkout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hdv.magiccoffee.R;
import com.hdv.magiccoffee.databinding.BottomSheetCheckoutBinding;
import com.hdv.magiccoffee.databinding.DialogConfirmBinding;
import com.hdv.magiccoffee.models.OrderProduct;
import com.hdv.magiccoffee.models.RedirectingData;
import com.hdv.magiccoffee.models.SaveCheckout;

public class CheckoutBottomSheet extends BottomSheetDialogFragment implements CheckoutProductAdapter.OnClickListener {

    BottomSheetCheckoutBinding binding;
    CheckoutProductAdapter checkoutProductAdapter;
    CheckoutViewModel checkoutViewModel;

    public CheckoutBottomSheet() {
    }

    @SuppressLint({"DefaultLocale", "ResourceAsColor"})
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
                    checkoutViewModel.visibleCheckoutBtn();
                    checkoutProductAdapter.reloadData(products);
                }
        );

        checkoutViewModel.getVisibleCheckoutBtn().observe(getViewLifecycleOwner(), it ->
                binding.checkoutBtn.setVisibility(it ? View.VISIBLE : View.INVISIBLE)
        );

        checkoutViewModel.getTotalPrice().observe(getViewLifecycleOwner(), totalPrice -> {
            checkoutViewModel.visibleCheckoutBtn();
            binding.priceDrinkTxt.setText(String.format("%.3fđ", totalPrice));
            binding.totalPriceTxt.setText(String.format("%.3fđ", SaveCheckout.checkoutPrice()));
            binding.checkoutPriceTxt.setText(String.format("%.3fđ", SaveCheckout.checkoutPrice()));
        });

        checkoutViewModel.getName().observe(getViewLifecycleOwner(), name -> {
            checkoutViewModel.visibleCheckoutBtn();
            if (name == null) {
                binding.nameTxt.setText(getString(R.string.please_enter_your_name));
                binding.nameTxt.setTextColor(Color.RED);
            } else {
                binding.nameTxt.setText(name);
                binding.nameTxt.setTextColor(Color.rgb(0, 0, 0));
            }
        });
        checkoutViewModel.getPhoneNumber().observe(getViewLifecycleOwner(), it -> {
            checkoutViewModel.visibleCheckoutBtn();
            if (it == null) {
                binding.phoneNumberTxt.setText(getString(R.string.please_enter_your_phone));
                binding.phoneNumberTxt.setTextColor(Color.RED);
            } else {
                binding.phoneNumberTxt.setText(it);
                binding.phoneNumberTxt.setTextColor(Color.rgb(0, 0, 0));
            }
        });
        checkoutViewModel.getAddress().observe(getViewLifecycleOwner(), it -> {
            checkoutViewModel.visibleCheckoutBtn();
            if (it == null) {
                binding.addressTxt.setText(getString(R.string.please_enter_your_address));
                binding.addressTxt.setTextColor(Color.RED);
            } else {
                binding.addressTxt.setText(it.getAddress());
                binding.addressTxt.setTextColor(Color.rgb(0, 0, 0));
            }
        });
        checkoutViewModel.getMessage().observe(getViewLifecycleOwner(), it -> {
            if (it.equals("Order success")) {
                checkoutViewModel.onDeleteAll();
                dismiss();
            }
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show();
        });
        checkoutViewModel.getIntentPaypal().observe(getViewLifecycleOwner(), it -> {
            if (!it.isEmpty()) {
                dismiss();
                checkoutViewModel.onDeleteAll();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(it));
                startActivity(intent);
            }
        });

        binding.paymentTxt.setText(SaveCheckout.methodPayment.getMethodPayment());

        if (SaveCheckout.voucher != null && SaveCheckout.voucher.getName() != null) {
            binding.voucherTxt.setText(SaveCheckout.voucher.getName());
        }

        binding.closeBtn.setOnClickListener(view -> dismiss());
        binding.addBtn.setOnClickListener(view -> dismiss());

        binding.editBtn.setOnClickListener(view ->
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_checkoutBottomSheet_to_editInfoShippingBottomSheet)
        );

        binding.voucherTxt.setOnClickListener(view ->
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_checkoutBottomSheet_to_selectVoucherBottomSheet)
        );

        binding.paymentTxt.setOnClickListener(view ->
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_checkoutBottomSheet_to_selectPaymentBottomSheet)
        );

        binding.checkoutBtn.setOnClickListener(view -> checkoutViewModel.checkout());

        binding.deleteBtn.setOnClickListener(view -> showDialog());

        return binding.getRoot();
    }

    @Override
    public void OnItemCheckoutClick(OrderProduct orderProduct, int position, View view) {
        Bundle bundle = new Bundle();
        RedirectingData redirectingData = new RedirectingData(orderProduct, "CHECKOUT_BOTTOM_SHEET", position);
        bundle.putSerializable("orderProduct", redirectingData);
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_checkoutBottomSheet_to_productBottomSheet, bundle);
    }

    @Override
    public void OnDeleteItem(int position) {
        checkoutViewModel.onDelete(position);
    }

    private void showDialog() {
        DialogConfirmBinding dialogConfirmBinding;
        dialogConfirmBinding = DialogConfirmBinding.inflate(LayoutInflater.from(requireContext()));
        AlertDialog alertDialog = new AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog).create();
        alertDialog.setView(dialogConfirmBinding.getRoot());

        dialogConfirmBinding.messageTxt.setText(getString(R.string.are_you_sure_you_want_to_delete_the_order));
        dialogConfirmBinding.acceptBtn.setText(getString(R.string.accept_text));
        dialogConfirmBinding.cancelBtn.setText(getString(R.string.cancel_text));

        dialogConfirmBinding.cancelBtn.setOnClickListener(view -> alertDialog.dismiss());
        dialogConfirmBinding.acceptBtn.setOnClickListener(view -> {
            checkoutViewModel.onDeleteAll();
            alertDialog.dismiss();
        });

        alertDialog.show();
    }
}
