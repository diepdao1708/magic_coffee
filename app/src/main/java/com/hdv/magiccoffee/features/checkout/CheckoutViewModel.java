package com.hdv.magiccoffee.features.checkout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CheckoutViewModel extends ViewModel {

    private final MutableLiveData<CheckoutUiState> _uiState = new MutableLiveData<>(new CheckoutUiState());

    public LiveData<CheckoutUiState> getUiState() {
        return _uiState;
    }
}
