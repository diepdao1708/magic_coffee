package com.hdv.magiccoffee.data.repositories;

import com.hdv.magiccoffee.data.models.CommonResponse;
import com.hdv.magiccoffee.data.models.user.UpdateUserRequest;
import com.hdv.magiccoffee.data.models.user.User;
import com.hdv.magiccoffee.data.services.ApiService;
import com.hdv.magiccoffee.data.services.UserService;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserRepository {
    private final UserService userService;


    public UserRepository() {
        userService = ApiService.getUserService();
    }

    public Single<User> getCurrentUser() {
        return userService.getCurrentUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<CommonResponse> updateUser(long id, String firstName, String lastName, String email, String phoneNum) {
        return userService.updateUser(id, new UpdateUserRequest(lastName + " " + firstName, email, phoneNum))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<CommonResponse> deleteUser(long id) {
        return userService.deleteUser(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
