package com.hdv.magiccoffee.data.services;

import com.hdv.magiccoffee.models.Product;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ProductService {

    @GET("/products/")
    Single<List<Product>> getAllProduct();

    @GET("/products/suggestion/")
    Single<List<Product>> getSuggestionProduct();
}
