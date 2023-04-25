package com.hdv.magiccoffee.models;

import com.hdv.magiccoffee.data.models.order.OrderProductResponse;
import com.hdv.magiccoffee.data.models.order.UserVoucher;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class Order implements Serializable {
    long id;
    double total;
    String address;
    List<OrderProductResponse> orderItems;
    String timeOrder;
    String paymentMethod;
    String receiverName;
    String receiverPhone;
    UserVoucher voucher;
    String status;
    int rating;

    public String getName() {
        return orderItems.stream().map(it -> it.getProduct().getName()).collect(Collectors.joining(" - "));
    }

    public Order() {
    }

    public Order(long id, double total, String address, List<OrderProductResponse> orderItems, String timeOrder, String paymentMethod, String receiverName, String receiverPhone, UserVoucher voucher, String status, int rating) {
        this.id = id;
        this.total = total;
        this.address = address;
        this.orderItems = orderItems;
        this.timeOrder = timeOrder;
        this.paymentMethod = paymentMethod;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.voucher = voucher;
        this.status = status;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    public String getAddress() {
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
