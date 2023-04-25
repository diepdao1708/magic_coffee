package com.hdv.magiccoffee.data.models.order;

import java.util.List;

public class OrderResponse {
    List<OrderHistoryResponse> data;
    int status;
    String message;

    public List<OrderHistoryResponse> getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
