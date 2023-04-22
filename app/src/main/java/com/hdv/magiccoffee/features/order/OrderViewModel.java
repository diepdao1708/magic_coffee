package com.hdv.magiccoffee.features.order;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hdv.magiccoffee.data.repositories.ProductRepository;
import com.hdv.magiccoffee.models.Product;
import com.hdv.magiccoffee.models.ProductCategory;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class OrderViewModel extends ViewModel {

    private final ProductRepository productRepository;

    public OrderViewModel() {
        productRepository = new ProductRepository();
    }

    private final MutableLiveData<List<Product>> _coffees = new MutableLiveData<>();

    public LiveData<List<Product>> getCoffees() {
        return _coffees;
    }

    private final MutableLiveData<List<Product>> _teas = new MutableLiveData<>();

    public LiveData<List<Product>> getTeas() {
        return _teas;
    }

    private final MutableLiveData<List<Product>> _fruits = new MutableLiveData<>();

    public LiveData<List<Product>> getFruits() {
        return _fruits;
    }

    private final MutableLiveData<List<Product>> _cakes = new MutableLiveData<>();

    public LiveData<List<Product>> getCakes() {
        return _cakes;
    }

    private final MutableLiveData<String> _message = new MutableLiveData<>();

    public LiveData<String> getMessage() {
        return _message;
    }

    public void getAllProduct() {
        productRepository.getAllProduct().subscribe(new SingleObserver<List<Product>>() {
            @Override
            public void onSubscribe(Disposable d) {
                // noop
            }

            @Override
            public void onSuccess(List<Product> products) {
                _coffees.postValue(
                        products.stream()
                                .filter(product -> product.getProductCategory().equals(ProductCategory.COFFEE.getProductCategory()))
                                .collect(Collectors.toList())
                );
                _cakes.postValue(
                        products.stream()
                                .filter(product -> product.getProductCategory().equals(ProductCategory.CAKE.getProductCategory()))
                                .collect(Collectors.toList())
                );
                _fruits.postValue(
                        products.stream()
                                .filter(product -> product.getProductCategory().equals(ProductCategory.FRUIT_JUICE.getProductCategory()))
                                .collect(Collectors.toList())
                );
                _teas.postValue(
                        products.stream()
                                .filter(product -> product.getProductCategory().equals(ProductCategory.TEA.getProductCategory()))
                                .collect(Collectors.toList())
                );
            }

            @Override
            public void onError(Throwable e) {
                _message.postValue(e.getMessage());
            }
        });
    }
}
