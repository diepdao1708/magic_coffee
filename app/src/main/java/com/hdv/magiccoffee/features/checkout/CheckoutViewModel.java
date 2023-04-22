package com.hdv.magiccoffee.features.checkout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hdv.magiccoffee.models.Account;
import com.hdv.magiccoffee.models.OrderProduct;
import com.hdv.magiccoffee.models.SaveAccount;
import com.hdv.magiccoffee.models.SaveCheckout;

import java.util.List;

public class CheckoutViewModel extends ViewModel {

    private final MutableLiveData<Account> _account = new MutableLiveData<>(new Account(
            SaveAccount.id,
            SaveAccount.image,
            SaveAccount.firstName,
            SaveAccount.lastName,
            SaveAccount.email,
            SaveAccount.phoneNumber,
            SaveAccount.address
    ));

    public LiveData<Account> getAccount() {
        return _account;
    }

    private final MutableLiveData<List<OrderProduct>> _products = new MutableLiveData<>(SaveCheckout.orderProducts);

    public LiveData<List<OrderProduct>> getProduct() {
        return _products;
    }

    private final MutableLiveData<Double> _totalPrice = new MutableLiveData<>(SaveCheckout.totalPrice());

    public LiveData<Double> getTotalPrice() {
        return _totalPrice;
    }

    public void onDelete(int index) {
        SaveCheckout.deleteProduct(index);
        _totalPrice.postValue(SaveCheckout.totalPrice());
        _products.postValue(SaveCheckout.orderProducts);
    }

    public void onDeleteAll() {
        SaveCheckout.deleteAllProduct();
        _totalPrice.postValue(SaveCheckout.totalPrice());
        _products.postValue(SaveCheckout.orderProducts);
    }
}
