package com.hdv.magiccoffee.data.services;

import com.hdv.magiccoffee.data.models.AddressRequest;
import com.hdv.magiccoffee.data.models.AddressResponse;
import com.hdv.magiccoffee.models.Address;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AddressService {

    @GET("/user/address/")
    Single<AddressResponse<List<Address>>> getAddress(@Query("userid") long id);

    @POST("/user/address/add/")
    Single<AddressResponse<Address>> addAddress(@Query("userid") long id, @Body AddressRequest addressRequest);

    @PUT("user/address/update/{id}")
    Single<AddressResponse<String>> updateAddress(@Path("id") long id, @Body AddressRequest addressRequest);

    @DELETE("/user/address/delete/{id}")
    Single<AddressResponse<String>> deleteAddress(@Path("id") long id);
}
