package com.hdv.magiccoffee.features.voucher;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hdv.magiccoffee.features.commondata.Voucher;

public class VoucherViewModel extends ViewModel {

    private final MutableLiveData<Voucher> _voucher = new MutableLiveData<>(new Voucher());

    public LiveData<Voucher> getVoucher() {
        return _voucher;
    }

    public void getVoucher(@Nullable Bundle bundle) {
        if (bundle != null) {
            Voucher voucher = (Voucher) bundle.getSerializable("voucher");
            _voucher.postValue(voucher);
        }
    }
}
