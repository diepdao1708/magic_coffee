package com.hdv.magiccoffee.features.address;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hdv.magiccoffee.data.models.AddressResponse;
import com.hdv.magiccoffee.data.models.CommonResponse;
import com.hdv.magiccoffee.data.repositories.AddressRepository;
import com.hdv.magiccoffee.models.Address;
import com.hdv.magiccoffee.models.SaveAccount;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class AddressViewModel extends ViewModel {

    private final AddressRepository addressRepository;

    public AddressViewModel() {
        addressRepository = new AddressRepository();
    }

    private final MutableLiveData<String> _message = new MutableLiveData<>();

    public LiveData<String> getMessage() {
        return _message;
    }

    private final MutableLiveData<List<Address>> _address = new MutableLiveData<>(SaveAccount.address);

    public LiveData<List<Address>> getAddress() {
        return _address;
    }

    private final MutableLiveData<Address> _editAddress = new MutableLiveData<>();

    public LiveData<Address> getEditAddress() {
        return _editAddress;
    }

    public void getUserAddress() {
        addressRepository.getAddress(SaveAccount.id)
                .subscribe(new SingleObserver<AddressResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // noop
                    }

                    @Override
                    public void onSuccess(AddressResponse response) {
                        if (response.getData() != null) {
                            SaveAccount.address = response.getData();
                            _address.postValue(response.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        _message.postValue(e.getMessage());
                    }
                });
    }

    public void addAddress(String address) {
        addressRepository.addAddress(SaveAccount.id, address)
                .subscribe(new SingleObserver<Address>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // noop
                    }

                    @Override
                    public void onSuccess(Address address) {
                        SaveAccount.address.add(address);
                        _address.postValue(SaveAccount.address);
                    }

                    @Override
                    public void onError(Throwable e) {
                        _message.postValue(e.getMessage());
                    }
                });
    }

    public void onUpdateAddress(Address address) {
        _editAddress.postValue(address);
    }

    public void updateAddress(long id, String address) {
        addressRepository.updateAddress(id, address)
                .subscribe(new SingleObserver<Address>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // noop
                    }

                    @Override
                    public void onSuccess(Address address) {
                        getUserAddress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        _message.postValue(e.getMessage());
                    }
                });
    }


    public void deleteAddress(long id) {
        addressRepository.deleteAddress(id)
                .subscribe(new SingleObserver<CommonResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // noop
                    }

                    @Override
                    public void onSuccess(CommonResponse response) {
                        getUserAddress();
                        _message.postValue(response.getMessage());
                    }

                    @Override
                    public void onError(Throwable e) {
                        _message.postValue(e.getMessage());
                    }
                });
    }
}
