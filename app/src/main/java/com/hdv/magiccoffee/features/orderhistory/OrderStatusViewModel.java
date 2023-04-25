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

public class OrderStatusViewModel extends ViewModel {

    OrderRepository orderRepository;

    public OrderStatusViewModel() {
        orderRepository = new OrderRepository();
    }

    private final MutableLiveData<Order> _order = new MutableLiveData<>(new Order());

    public LiveData<Order> getOrder() {
        return _order;
    }

    public void getData(@Nullable Bundle bundle) {
        if (bundle != null) {
            Order order = (Order) bundle.getSerializable("order");
            _order.postValue(order);
        }
    }

    public void cancelOrder(long id) {
        orderRepository.cancelOrder(id)
                .subscribe(new SingleObserver<CommonResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(CommonResponse commonResponse) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
