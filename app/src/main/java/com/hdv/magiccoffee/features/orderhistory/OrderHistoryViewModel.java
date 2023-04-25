package com.hdv.magiccoffee.features.orderhistory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hdv.magiccoffee.data.models.order.OrderResponse;
import com.hdv.magiccoffee.data.repositories.OrderRepository;
import com.hdv.magiccoffee.models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class OrderHistoryViewModel extends ViewModel {

    OrderRepository orderRepository;

    public OrderHistoryViewModel() {
        orderRepository = new OrderRepository();
    }

    private final MutableLiveData<List<Order>> _orders = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Order>> getOrders() {
        return _orders;
    }

    private final MutableLiveData<String> _message = new MutableLiveData<>();

    public LiveData<String> getString() {
        return _message;
    }

    public void getOrderHistory(long userId) {
        orderRepository.getOrderHistory(userId)
                .subscribe(new SingleObserver<OrderResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // noop
                    }

                    @Override
                    public void onSuccess(OrderResponse response) {
                        _orders.postValue(
                                response.getData().stream().map(it -> new Order(
                                        it.getId(),
                                        it.getTotal(),
                                        it.getAddress() == null ? "" : it.getAddress().getAddress(),
                                        it.getOrderItems(),
                                        it.getTimeOrder(),
                                        it.getPaymentMethod(),
                                        it.getReceiverName(),
                                        it.getReceiverPhone(),
                                        it.getVoucher(),
                                        it.getStatus(),
                                        it.getRating()
                                )).collect(Collectors.toList())
                        );
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
