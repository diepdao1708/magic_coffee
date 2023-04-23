package com.hdv.magiccoffee.features.checkout;

import androidx.lifecycle.ViewModel;

import com.hdv.magiccoffee.data.repositories.AddressRepository;
import com.hdv.magiccoffee.models.Address;
import com.hdv.magiccoffee.models.SaveAccount;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class EditInfoShippingViewModel extends ViewModel {

    private final AddressRepository addressRepository;

    public EditInfoShippingViewModel() {
        addressRepository = new AddressRepository();
    }

    public void addAddress(String address) {
        addressRepository.addAddress(SaveAccount.id, address)
                .subscribe(new SingleObserver<Address>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // noop
                    }

                    @Override
                    public void onSuccess(Address response) {
                        SaveAccount.address.add(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // noop
                    }
                });
    }
}
