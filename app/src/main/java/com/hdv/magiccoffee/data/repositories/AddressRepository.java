package com.hdv.magiccoffee.data.repositories;

import com.hdv.magiccoffee.data.models.AddressRequest;
import com.hdv.magiccoffee.data.models.AddressResponse;
import com.hdv.magiccoffee.data.models.CommonResponse;
import com.hdv.magiccoffee.data.services.AddressService;
import com.hdv.magiccoffee.data.services.ApiService;
import com.hdv.magiccoffee.models.Address;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddressRepository {
    private final AddressService addressService;

    public AddressRepository() {
        addressService = ApiService.getAddressService();
    }

    public Single<AddressResponse> getAddress(long id) {
        return addressService.getAddress(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Address> addAddress(long id, String address) {
        AddressRequest addressRequest = new AddressRequest(address);
        return addressService.addAddress(id, addressRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Single<Address> updateAddress(long id, String address) {
        AddressRequest addressRequest = new AddressRequest(address);
        return addressService.updateAddress(id, addressRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Single<CommonResponse> deleteAddress(long id) {
        return addressService.deleteAddress(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
