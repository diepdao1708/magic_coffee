package com.hdv.magiccoffee.data.services;

import com.hdv.magiccoffee.data.models.CommonResponse;
import com.hdv.magiccoffee.data.models.OrderRequest;
import com.hdv.magiccoffee.data.models.PaypalResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface OrderService {

    @POST("/order/order_pay_by_cash/")
    Single<CommonResponse> orderByCash(@Query("user_id") long id, @Body OrderRequest orderRequest);

    @POST("/order/order_pay_by_paypal/")
    Single<PaypalResponse> orderByPaypal(@Query("user_id") long id, @Body OrderRequest orderRequest);

    @PUT("/order/cancel/")
    Single<CommonResponse> cancelOrder(@Query("id") long id);

    @GET("/order/pay/cancel")
    Single<CommonResponse> cancelPay(@Query("token") String token);

    @GET("/order/pay/success")
    Single<CommonResponse> successPay(@Query("token") String token, @Query("paymentId") String paymentId, @Query("PayerID") String payerId);
}
