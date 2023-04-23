package com.hdv.magiccoffee.data.models;

import com.hdv.magiccoffee.models.Address;

import java.util.List;

public class AddressResponse {
    List<Address> data;
    int status;
    String message;

    public List<Address> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
