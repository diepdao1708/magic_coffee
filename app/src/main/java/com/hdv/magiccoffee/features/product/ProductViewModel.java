package com.hdv.magiccoffee.features.product;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hdv.magiccoffee.features.commondata.Product;

public class ProductViewModel extends ViewModel {

    private final MutableLiveData<Product> _uiState = new MutableLiveData<>(new Product());

    public LiveData<Product> getUiState() {
        return _uiState;
    }

    public void getProduct(@Nullable Bundle bundle) {
        if (bundle != null) {
            Product product = (Product) bundle.getSerializable("product");
            _uiState.postValue(product);
        }
    }
}
