package com.hdv.magiccoffee.features.voucher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hdv.magiccoffee.R;
import com.hdv.magiccoffee.databinding.BottomSheetSelectVoucherBinding;
import com.hdv.magiccoffee.features.home.VoucherAdapter;
import com.hdv.magiccoffee.models.SaveAccount;
import com.hdv.magiccoffee.models.SaveCheckout;
import com.hdv.magiccoffee.models.Voucher;

public class SelectVoucherBottomSheet extends BottomSheetDialogFragment implements VoucherAdapter.OnClickListener {
    BottomSheetSelectVoucherBinding binding;
    VoucherAdapter adapter;

    public SelectVoucherBottomSheet() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetSelectVoucherBinding.inflate(inflater, container, false);
        adapter = new VoucherAdapter(this);
        binding.recyclerView.setAdapter(adapter);
        adapter.reloadData(SaveAccount.vouchers);

        binding.backBtn.setOnClickListener(view -> dismiss());

        return binding.getRoot();
    }

    @Override
    public void OnItemVoucherClick(Voucher voucher, View view) {
        SaveCheckout.voucher = voucher;
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_selectVoucherBottomSheet_to_checkoutBottomSheet);
        dismiss();
    }
}
