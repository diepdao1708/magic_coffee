package com.hdv.magiccoffee.features.checkout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hdv.magiccoffee.models.Address;
import com.hdv.magiccoffee.models.OrderProduct;
import com.hdv.magiccoffee.models.SaveCheckout;

import java.util.List;

public class CheckoutViewModel extends ViewModel {

    private final MutableLiveData<String> _name = new MutableLiveData<>(SaveCheckout.name);

    public LiveData<String> getName() {
        return _name;
    }

    private final MutableLiveData<Address> _address = new MutableLiveData<>(SaveCheckout.address);

    public LiveData<Address> getAddress() {
        return _address;
    }

    private final MutableLiveData<String> _phoneNumber = new MutableLiveData<>(SaveCheckout.phoneNumber);

    public LiveData<String> getPhoneNumber() {
        return _phoneNumber;
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

    public void checkout() {

    }
}
