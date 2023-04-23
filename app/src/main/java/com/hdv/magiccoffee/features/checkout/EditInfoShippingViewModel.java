package com.hdv.magiccoffee.features.checkout;

import androidx.lifecycle.ViewModel;

import com.hdv.magiccoffee.data.models.AddressResponse;
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
                .subscribe(new SingleObserver<AddressResponse<Address>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // noop
                    }

                    @Override
                    public void onSuccess(AddressResponse response) {
                        SaveAccount.address.add((Address) response.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        // noop
                    }
                });
    }
}
