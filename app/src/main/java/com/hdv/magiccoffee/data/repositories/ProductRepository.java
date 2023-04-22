package com.hdv.magiccoffee.data.repositories;

import com.hdv.magiccoffee.data.services.ApiService;
import com.hdv.magiccoffee.data.services.ProductService;
import com.hdv.magiccoffee.models.Product;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProductRepository {

    private final ProductService productService;

    public ProductRepository() {
        productService = ApiService.getProductService();
    }

    public Single<List<Product>> getAllProduct() {
        return productService.getAllProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<Product>> getSuggestionProduct() {
        return productService.getSuggestionProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
