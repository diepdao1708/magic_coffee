package com.hdv.magiccoffee.data.models;

public class AddressResponse<T> {
    T data;
    int status;
    String message;

    public Object getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
