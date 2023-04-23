package com.hdv.magiccoffee.data.models;

import com.google.gson.annotations.SerializedName;

public class OrderItemRequest {
    @SerializedName("product_id")
    String productId;
    String size;
    String topping;
    int quantity;

    public OrderItemRequest(String productId, String size, String topping, int quantity) {
        this.productId = productId;
        this.size = size;
        this.topping = topping;
        this.quantity = quantity;
    }
}
