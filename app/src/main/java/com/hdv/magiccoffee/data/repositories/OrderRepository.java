package com.hdv.magiccoffee.data.repositories;

import com.hdv.magiccoffee.data.models.CommonResponse;
import com.hdv.magiccoffee.data.models.OrderRequest;
import com.hdv.magiccoffee.data.models.PaypalResponse;
import com.hdv.magiccoffee.data.models.order.OrderResponse;
import com.hdv.magiccoffee.data.services.ApiService;
import com.hdv.magiccoffee.data.services.OrderService;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OrderRepository {

    OrderService orderService;

    public OrderRepository() {
        this.orderService = ApiService.getOrderService();
    }

    public Single<CommonResponse> orderByCash(long id, OrderRequest orderRequest) {
        return orderService.orderByCash(id, orderRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<PaypalResponse> orderByPaypal(long id, OrderRequest orderRequest) {
        return orderService.orderByPaypal(id, orderRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<CommonResponse> cancelOrder(long id) {
        return orderService.cancelOrder(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<CommonResponse> cancelPay(String token) {
        return orderService.cancelPay(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<CommonResponse> successPay(String token, String paymentId, String payerId) {
        return orderService.successPay(token, paymentId, payerId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<OrderResponse> getOrderHistory(long id) {
        return orderService.getOrderHistory(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<CommonResponse> ratingOrder(long id, int rate) {
        return orderService.ratingOrder(id, rate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
