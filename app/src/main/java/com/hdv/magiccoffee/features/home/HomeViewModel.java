package com.hdv.magiccoffee.features.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hdv.magiccoffee.data.repositories.ProductRepository;
import com.hdv.magiccoffee.models.Product;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class HomeViewModel extends ViewModel {

    private final ProductRepository productRepository;

    public HomeViewModel() {
        productRepository = new ProductRepository();
    }

    private final MutableLiveData<List<Product>> _suggestion = new MutableLiveData<>();

    public LiveData<List<Product>> getSuggestion() {
        return _suggestion;
    }

    private final MutableLiveData<String> _message = new MutableLiveData<>();

    public LiveData<String> getMessage() {
        return _message;
    }

    public void getSuggestionProduct() {
        productRepository.getSuggestionProduct().subscribe(new SingleObserver<List<Product>>() {
            @Override
            public void onSubscribe(Disposable d) {
                // noop
            }

            @Override
            public void onSuccess(List<Product> products) {
                _suggestion.postValue(products);
            }

            @Override
            public void onError(Throwable e) {
                _message.postValue(e.getMessage());
            }
        });
    }
}
