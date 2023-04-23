package com.hdv.magiccoffee.data.services;

import com.hdv.magiccoffee.models.Header;
import com.hdv.magiccoffee.models.Voucher;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface HomeService {

    @GET("/home/headers/")
    Single<List<Header>> getHeaders();

    @GET("/vouchers/")
    Single<List<Voucher>> getUsersVouchers();
}
