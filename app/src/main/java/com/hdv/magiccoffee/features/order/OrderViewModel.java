package com.hdv.magiccoffee.features.order;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrderViewModel extends ViewModel {

    private final MutableLiveData<OrderUiState> _uiState = new MutableLiveData<>(new OrderUiState());

    public LiveData<OrderUiState> getUiState() {
        return _uiState;
    }
}
