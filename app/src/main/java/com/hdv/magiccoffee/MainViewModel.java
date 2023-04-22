package com.hdv.magiccoffee;

import androidx.lifecycle.ViewModel;

import com.hdv.magiccoffee.data.models.AddressResponse;
import com.hdv.magiccoffee.data.repositories.AddressRepository;
import com.hdv.magiccoffee.data.repositories.UserRepository;
import com.hdv.magiccoffee.models.Address;
import com.hdv.magiccoffee.models.SaveAccount;
import com.hdv.magiccoffee.models.User;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class MainViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public MainViewModel() {
        userRepository = new UserRepository();
        addressRepository = new AddressRepository();
    }

    public void getCurrentUser() {
        userRepository.getCurrentUser()
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // noop
                    }

                    @Override
                    public void onSuccess(User user) {
                        String[] name = user.getFullname().split(" ");
                        String firstName = name[name.length - 1];
                        StringBuilder lastName = new StringBuilder();
                        for (int i = 0; i < name.length - 1; i++) {
                            lastName.append(name[i]).append(" ");
                        }
                        getUserAddress(user.getId());
                        SaveAccount.id = user.getId();
                        SaveAccount.image = user.getAvatarLink();
                        SaveAccount.firstName = firstName;
                        SaveAccount.lastName = lastName.toString();
                        SaveAccount.email = user.getEmail();
                        SaveAccount.phoneNumber = user.getPhoneNum();
                    }

                    @Override
                    public void onError(Throwable e) {
                        // noop
                    }
                });
    }

    private void getUserAddress(long id) {
        addressRepository.getAddress(id)
                .subscribe(new SingleObserver<AddressResponse<List<Address>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // noop
                    }

                    @Override
                    public void onSuccess(AddressResponse<List<Address>> response) {
                        if (response.getData() != null) {
                            SaveAccount.address = (List<Address>) response.getData();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        // noop
                    }
                });
    }
}
