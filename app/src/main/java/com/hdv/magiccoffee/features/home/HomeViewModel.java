package com.hdv.magiccoffee.features.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<HomeUiState> _uiState = new MutableLiveData<>(new HomeUiState());

    public LiveData<HomeUiState> getUiState() {
        return _uiState;
    }
}
