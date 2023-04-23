package com.hdv.magiccoffee.data.repositories;

import com.hdv.magiccoffee.data.models.CommonResponse;
import com.hdv.magiccoffee.data.models.OrderRequest;
import com.hdv.magiccoffee.data.models.PaypalResponse;
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
}
