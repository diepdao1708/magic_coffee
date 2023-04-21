package com.hdv.magiccoffee.data.services;

import com.hdv.magiccoffee.data.models.CommonResponse;
import com.hdv.magiccoffee.data.models.user.UpdateUserRequest;
import com.hdv.magiccoffee.data.models.user.User;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface UserService {

    @GET("/user")
    Single<User> getCurrentUser();

    @PATCH("/user/update/{id}")
    Single<CommonResponse> updateUser(@Path("id") long id, @Body UpdateUserRequest updateUserRequest);

    @DELETE("/user/delete/{id}")
    Single<CommonResponse> deleteUser(@Path("id") long id);
}
