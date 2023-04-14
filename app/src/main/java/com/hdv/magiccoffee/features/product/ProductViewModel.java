package com.hdv.magiccoffee.features.product;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hdv.magiccoffee.data.models.SaveCheckout;
import com.hdv.magiccoffee.features.commondata.Product;
import com.hdv.magiccoffee.features.commondata.RedirectingData;
import com.hdv.magiccoffee.features.commondata.Size;
import com.hdv.magiccoffee.features.commondata.Topping;

public class ProductViewModel extends ViewModel {
    private final MutableLiveData<Product> _product = new MutableLiveData<>(new Product());

    public LiveData<Product> getProduct() {
        return _product;
    }

    private final MutableLiveData<RedirectingData> _redirectingData = new MutableLiveData<>(new RedirectingData());
    private final MutableLiveData<Size> _size = new MutableLiveData<>(Size.MEDIUM);
    private final MutableLiveData<Topping> _topping = new MutableLiveData<>(Topping.NONE);
    private final MutableLiveData<Double> _price = new MutableLiveData<>(null);
    private final MutableLiveData<Integer> _quantity = new MutableLiveData<>(null);

    public LiveData<Integer> getQuantity() {
        return _quantity;
    }

    private final MutableLiveData<Double> _totalPrice = new MutableLiveData<>(null);

    public LiveData<Double> getTotalPrice() {
        return _totalPrice;
    }

    public void getProduct(@Nullable Bundle bundle) {
        if (bundle != null) {
            RedirectingData redirectingData = (RedirectingData) bundle.getSerializable("product");
            Product product = redirectingData.getProduct();
            _redirectingData.setValue(redirectingData);
            _product.postValue(product);
            _quantity.postValue(product.getQuantity());
            _price.setValue(product.getPrice() / product.getQuantity());
            _totalPrice.postValue(product.getPrice());
        }
    }

    public void onCheckedSize(Size size) {
        _size.setValue(size);
        if (_product.getValue() != null && _quantity.getValue() != null && _topping.getValue() != null) {
            _price.setValue(size.getPrice(_product.getValue().getCost()) + _topping.getValue().getPrice());
        }
        if (_price.getValue() != null && _quantity.getValue() != null) {
            _totalPrice.postValue(_price.getValue() * _quantity.getValue());
        }
    }

    public void onCheckedTopping(Topping topping) {
        _topping.setValue(topping);
        if (_product.getValue() != null && _quantity.getValue() != null && _topping.getValue() != null && _size.getValue() != null) {
            _price.setValue(_size.getValue().getPrice(_product.getValue().getCost()) + _topping.getValue().getPrice());
        }
        if (_price.getValue() != null && _quantity.getValue() != null) {
            _totalPrice.postValue(_price.getValue() * _quantity.getValue());
        }
    }

    public void onIncreaseQuantity() {
        if (_quantity.getValue() != null && _price.getValue() != null) {
            _quantity.postValue(_quantity.getValue() + 1);
            _totalPrice.postValue(_price.getValue() * (_quantity.getValue() + 1));
        }
    }

    public void onDecreaseQuantity() {
        if (_quantity.getValue() != null && _price.getValue() != null && _quantity.getValue() > 1) {
            _quantity.postValue(_quantity.getValue() - 1);
            _totalPrice.postValue(_price.getValue() * (_quantity.getValue() - 1));
        }
    }

    public void onUpdateCheckout() {
        if (_product.getValue() != null && _quantity.getValue() != null && _size.getValue() != null && _topping.getValue() != null) {
            Product product = new Product(
                    _product.getValue().getId(),
                    _product.getValue().getImage(),
                    _product.getValue().getName(),
                    _product.getValue().getCost(),
                    _product.getValue().getDescription(),
                    _quantity.getValue(),
                    _size.getValue(),
                    _topping.getValue()
            );
            if (_quantity.getValue() == 0 && _redirectingData.getValue() != null) {
                SaveCheckout.deleteProduct(_redirectingData.getValue().getIndex());
            } else if (_redirectingData.getValue() != null && _redirectingData.getValue().getScreen().equals("CHECKOUT_BOTTOM_SHEET")) {
                SaveCheckout.updateProduct(product, _redirectingData.getValue().getIndex());
            } else {
                SaveCheckout.addProduct(product);
            }
        }
    }
}
