package com.hdv.magiccoffee.features.checkout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hdv.magiccoffee.data.models.Account;
import com.hdv.magiccoffee.data.models.SaveAccount;
import com.hdv.magiccoffee.data.models.SaveCheckout;
import com.hdv.magiccoffee.features.commondata.Product;

import java.util.List;

public class CheckoutViewModel extends ViewModel {

    private final MutableLiveData<Account> _account = new MutableLiveData<>(SaveAccount.account);

    public LiveData<Account> getAccount() {
        return _account;
    }

    private final MutableLiveData<List<Product>> _products = new MutableLiveData<>(SaveCheckout.products);

    public LiveData<List<Product>> getProduct() {
        return _products;
    }

    private final MutableLiveData<Double> _totalPrice = new MutableLiveData<>(SaveCheckout.totalPrice());

    public LiveData<Double> getTotalPrice() {
        return _totalPrice;
    }

    public void onDelete(int index) {
        SaveCheckout.deleteProduct(index);
        _totalPrice.postValue(SaveCheckout.totalPrice());
        _products.postValue(SaveCheckout.products);
    }

    public void onDeleteAll() {
        SaveCheckout.deleteAllProduct();
        _totalPrice.postValue(SaveCheckout.totalPrice());
        _products.postValue(SaveCheckout.products);
    }
}
