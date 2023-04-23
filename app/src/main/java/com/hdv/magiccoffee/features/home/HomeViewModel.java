package com.hdv.magiccoffee.features.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hdv.magiccoffee.data.repositories.HomeRepository;
import com.hdv.magiccoffee.data.repositories.ProductRepository;
import com.hdv.magiccoffee.models.Header;
import com.hdv.magiccoffee.models.Product;
import com.hdv.magiccoffee.models.SaveAccount;
import com.hdv.magiccoffee.models.Voucher;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class HomeViewModel extends ViewModel {

    private final ProductRepository productRepository;
    private final HomeRepository homeRepository;

    public HomeViewModel() {
        productRepository = new ProductRepository();
        homeRepository = new HomeRepository();
    }

    private final MutableLiveData<List<Product>> _suggestion = new MutableLiveData<>();

    public LiveData<List<Product>> getSuggestion() {
        return _suggestion;
    }

    private final MutableLiveData<String> _message = new MutableLiveData<>();

    public LiveData<String> getMessage() {
        return _message;
    }

    private final MutableLiveData<List<Header>> _headers = new MutableLiveData<>();

    public LiveData<List<Header>> getHears() {
        return _headers;
    }

    private final MutableLiveData<List<Voucher>> _vouchers = new MutableLiveData<>();

    public LiveData<List<Voucher>> getVouchers() {
        return _vouchers;
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

    public void getHeaders() {
        homeRepository.getHeaders().subscribe(new SingleObserver<List<Header>>() {
            @Override
            public void onSubscribe(Disposable d) {
                // noop
            }

            @Override
            public void onSuccess(List<Header> headers) {
                _headers.postValue(headers);
            }

            @Override
            public void onError(Throwable e) {
                _message.postValue(e.getMessage());
            }
        });
    }

    public void getUsersVouchers() {
        homeRepository.getUsersVouchers().subscribe(new SingleObserver<List<Voucher>>() {
            @Override
            public void onSubscribe(Disposable d) {
                // noop
            }

            @Override
            public void onSuccess(List<Voucher> vouchers) {
                _vouchers.postValue(vouchers);
                SaveAccount.vouchers = vouchers;
            }

            @Override
            public void onError(Throwable e) {
                _message.postValue(e.getMessage());
            }
        });
    }
}
