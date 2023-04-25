package com.hdv.magiccoffee.data.models.order;

import com.google.gson.annotations.SerializedName;
import com.hdv.magiccoffee.models.Address;

import java.util.List;

public class OrderHistoryResponse {
    long id;
    double total;
    Address address;
    @SerializedName("order_items")
    List<OrderProductResponse> orderItems;
    @SerializedName("time_order")
    String timeOrder;
    String paymentMethod;
    @SerializedName("receiver_name")
    String receiverName;
    @SerializedName("receiver_phone")
    String receiverPhone;
    UserVoucher voucher;
    String status;
    int rating;

    public long getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    public Address getAddress() {
        return address;
    }

    public List<OrderProductResponse> getOrderItems() {
        return orderItems;
    }

    public String getTimeOrder() {
        return timeOrder;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public UserVoucher getVoucher() {
        return voucher;
    }

    public String getStatus() {
        return status;
    }

    public int getRating() {
        return rating;
    }
}
