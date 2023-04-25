package com.hdv.magiccoffee.features.orderhistory;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hdv.magiccoffee.data.models.CommonResponse;
import com.hdv.magiccoffee.data.repositories.OrderRepository;
import com.hdv.magiccoffee.models.Order;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class OrderReviewViewModel extends ViewModel {

    OrderRepository orderRepository;

    public OrderReviewViewModel() {
        orderRepository = new OrderRepository();
    }

    private final MutableLiveData<Order> _order = new MutableLiveData<>(null);

    public LiveData<Order> getOrder() {
        return _order;
    }

    private final MutableLiveData<String> _message = new MutableLiveData<>();

    public LiveData<String> getMessage() {
        return _message;
    }

    public void getCurrentOrder(@Nullable Bundle bundle) {
        if (bundle != null) {
            Order order = (Order) bundle.getSerializable("order");
            _order.postValue(order);
        }
    }

    public void ratingOrder(int rate) {
        if (_order.getValue() != null) {
            orderRepository.ratingOrder(_order.getValue().getId(), rate)
                    .subscribe(new SingleObserver<CommonResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            // noop
                        }

                        @Override
                        public void onSuccess(CommonResponse commonResponse) {
                            _message.postValue("Success");
                        }

                        @Override
                        public void onError(Throwable e) {
                            // noop
                        }
                    });
        }
    }
}
