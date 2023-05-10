package com.hdv.magiccoffee.features.checkout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hdv.magiccoffee.data.models.CommonResponse;
import com.hdv.magiccoffee.data.models.OrderItemRequest;
import com.hdv.magiccoffee.data.models.OrderRequest;
import com.hdv.magiccoffee.data.models.PaypalResponse;
import com.hdv.magiccoffee.data.repositories.OrderRepository;
import com.hdv.magiccoffee.models.Address;
import com.hdv.magiccoffee.models.MethodPayment;
import com.hdv.magiccoffee.models.OrderProduct;
import com.hdv.magiccoffee.models.SaveAccount;
import com.hdv.magiccoffee.models.SaveCheckout;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class CheckoutViewModel extends ViewModel {

    OrderRepository orderRepository;

    public CheckoutViewModel() {
        orderRepository = new OrderRepository();
    }

    private final MutableLiveData<String> _name = new MutableLiveData<>(SaveCheckout.name);

    public LiveData<String> getName() {
        return _name;
    }

    private final MutableLiveData<Address> _address = new MutableLiveData<>(SaveCheckout.address);

    public LiveData<Address> getAddress() {
        return _address;
    }

    private final MutableLiveData<String> _phoneNumber = new MutableLiveData<>(SaveCheckout.phoneNumber);

    public LiveData<String> getPhoneNumber() {
        return _phoneNumber;
    }

    private final MutableLiveData<List<OrderProduct>> _products = new MutableLiveData<>(SaveCheckout.orderProducts);

    public LiveData<List<OrderProduct>> getProduct() {
        return _products;
    }

    private final MutableLiveData<Double> _totalPrice = new MutableLiveData<>(SaveCheckout.totalPrice());

    public LiveData<Double> getTotalPrice() {
        return _totalPrice;
    }

    private final MutableLiveData<String> _message = new MutableLiveData<>();

    public LiveData<String> getMessage() {
        return _message;
    }

    private final MutableLiveData<Boolean> _visibleCheckoutBtn = new MutableLiveData<>();

    public LiveData<Boolean> getVisibleCheckoutBtn() {
        return _visibleCheckoutBtn;
    }

    private final MutableLiveData<String> _intentPaypal = new MutableLiveData<>("");

    public LiveData<String> getIntentPaypal() {
        return _intentPaypal;
    }

    public void onDelete(int index) {
        SaveCheckout.deleteProduct(index);
        _totalPrice.postValue(SaveCheckout.totalPrice());
        _products.postValue(SaveCheckout.orderProducts);
    }

    public void onDeleteAll() {
        SaveCheckout.deleteAllProduct();
        SaveCheckout.voucher = null;
        _totalPrice.postValue(SaveCheckout.totalPrice());
        _products.postValue(SaveCheckout.orderProducts);
    }

    public void checkout() {
        if (SaveCheckout.methodPayment == MethodPayment.PAYPAL) {
            orderByPaypal();
        } else {
            orderByCash();
        }
    }

    public void visibleCheckoutBtn() {
        if (_products.getValue() != null && _products.getValue().size() > 0 &&
                _name.getValue() != null && !_name.getValue().isEmpty() &&
                _address.getValue() != null && _address.getValue() != null &&
                _phoneNumber.getValue() != null && !_phoneNumber.getValue().isEmpty()) {
            _visibleCheckoutBtn.postValue(true);
        } else {
            _visibleCheckoutBtn.postValue(false);
        }
    }

    private void orderByCash() {
        OrderRequest orderRequest = new OrderRequest(
                SaveCheckout.checkoutPrice(),
                SaveCheckout.address.getId(),
                SaveCheckout.voucher == null ? 0 : SaveCheckout.voucher.getId(),
                SaveCheckout.orderProducts.stream().map(it -> new OrderItemRequest(
                        it.getProductId(),
                        it.getSize().getSize(),
                        it.getTopping().getTopping(),
                        it.getQuantity()
                )).collect(Collectors.toList()),
                _name.getValue(),
                _phoneNumber.getValue()
        );
        orderRepository.orderByCash(SaveAccount.id, orderRequest)
                .subscribe(new SingleObserver<CommonResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // noop
                    }

                    @Override
                    public void onSuccess(CommonResponse commonResponse) {
                        _message.postValue(commonResponse.getMessage());
                    }

                    @Override
                    public void onError(Throwable e) {
                        _message.postValue(e.getMessage());
                    }
                });
    }

    private void orderByPaypal() {
        OrderRequest orderRequest = new OrderRequest(
                SaveCheckout.checkoutPrice(),
                SaveCheckout.address.getId(),
                SaveCheckout.voucher == null  ? 0 : SaveCheckout.voucher.getId(),
                SaveCheckout.orderProducts.stream().map(it -> new OrderItemRequest(
                        it.getProductId(),
                        it.getSize().getSize(),
                        it.getTopping().getTopping(),
                        it.getQuantity()
                )).collect(Collectors.toList()),
                _name.getValue(),
                _phoneNumber.getValue());
        orderRepository.orderByPaypal(SaveAccount.id, orderRequest)
                .subscribe(new SingleObserver<PaypalResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // noop
                    }

                    @Override
                    public void onSuccess(PaypalResponse paypalResponse) {
                        _intentPaypal.postValue(paypalResponse.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        _message.postValue(e.getMessage());
                    }
                });
    }
}
