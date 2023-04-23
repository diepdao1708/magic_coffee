package com.hdv.magiccoffee.data.repositories;

import com.hdv.magiccoffee.data.services.ApiService;
import com.hdv.magiccoffee.data.services.HomeService;
import com.hdv.magiccoffee.models.Header;
import com.hdv.magiccoffee.models.Voucher;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeRepository {
    private final HomeService homeService;

    public HomeRepository() {
        this.homeService = ApiService.getHomeService();
    }

    public Single<List<Header>> getHeaders() {
        return homeService.getHeaders()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<Voucher>> getUsersVouchers() {
        return homeService.getUsersVouchers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
