package com.hdv.magiccoffee;

import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.hdv.magiccoffee.data.models.AddressResponse;
import com.hdv.magiccoffee.data.models.CommonResponse;
import com.hdv.magiccoffee.data.repositories.AddressRepository;
import com.hdv.magiccoffee.data.repositories.OrderRepository;
import com.hdv.magiccoffee.data.repositories.UserRepository;
import com.hdv.magiccoffee.models.SaveAccount;
import com.hdv.magiccoffee.models.User;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

@HiltViewModel
public class MainViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final OrderRepository orderRepository;

    private final GoogleSignInClient googleSignInClient;

    @Inject
    public MainViewModel(GoogleSignInClient googleSignInClient) {
        this.googleSignInClient = googleSignInClient;
        userRepository = new UserRepository();
        addressRepository = new AddressRepository();
        orderRepository = new OrderRepository();
    }

    private final MutableLiveData<CommonResponse> _showDialog = new MutableLiveData<>(null);

    public LiveData<CommonResponse> showDialog() {
        return _showDialog;
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
                        if (user.getFullname() != null) {
                            String[] name = user.getFullname().split(" ");
                            String firstName = name[name.length - 1];
                            StringBuilder lastName = new StringBuilder();
                            for (int i = 0; i < name.length - 1; i++) {
                                lastName.append(name[i]).append(" ");
                            }
                            SaveAccount.firstName = firstName;
                            SaveAccount.lastName = lastName.toString();
                        }
                        getUserAddress(user.getId());
                        SaveAccount.id = user.getId();
                        SaveAccount.image = user.getAvatarLink();
                        SaveAccount.email = user.getEmail();
                        SaveAccount.phoneNumber = user.getPhoneNum();

                    }

                    @Override
                    public void onError(Throwable e) {
                        // noop
                    }
                });
    }

    public void logOut() {
        googleSignInClient.signOut();
    }

    private void getUserAddress(long id) {
        addressRepository.getAddress(id)
                .subscribe(new SingleObserver<AddressResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // noop
                    }

                    @Override
                    public void onSuccess(AddressResponse response) {
                        if (response.getData() != null) {
                            SaveAccount.address = response.getData();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        // noop
                    }
                });
    }

    public void checkOrder(Uri uri) {
        String u = uri.toString();
        Log.d("TAG", u);
        String[] a = u.split("\\?");
        String[] b = a[1].split("&");
        String[] paymentId = b[0].split("=");
        String[] token = b[1].split("=");
        String[] payerID = b[2].split("=");
        if (u.contains("success")) {
            successPay(token[1].trim(), paymentId[1].trim(), payerID[1].trim());
        } else {
            cancelPay(token[1].trim());
        }
    }

    private void cancelPay(String token) {
        orderRepository.cancelPay(token)
                .subscribe(new SingleObserver<CommonResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // noop
                    }

                    @Override
                    public void onSuccess(CommonResponse commonResponse) {
                        _showDialog.postValue(commonResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // noop
                    }
                });
    }

    private void successPay(String token, String paymentId, String payerId) {
        orderRepository.successPay(token, paymentId, payerId)
                .subscribe(new SingleObserver<CommonResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // noop
                    }

                    @Override
                    public void onSuccess(CommonResponse commonResponse) {
                        _showDialog.postValue(commonResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // noop
                    }
                });
    }
}
