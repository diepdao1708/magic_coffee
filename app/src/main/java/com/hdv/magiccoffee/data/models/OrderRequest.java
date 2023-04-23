package com.hdv.magiccoffee.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderRequest {
    double total;

    @SerializedName("address_id")
    long addressId;

    @SerializedName("user_voucher_id")
    int userVoucherId;

    @SerializedName("order_items")
    List<OrderItemRequest> orderItems;

    @SerializedName("receiver_name")
    String receiverName;

    @SerializedName("receiver_phone")
    String receiverPhone;

    public OrderRequest(double total, long addressId, int userVoucherId, List<OrderItemRequest> orderItems, String receiverName, String receiverPhone) {
        this.total = total;
        this.addressId = addressId;
        this.userVoucherId = userVoucherId;
        this.orderItems = orderItems;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
    }
}
