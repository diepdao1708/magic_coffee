package com.hdv.magiccoffee.models;

public class Address {
    long id;
    String address;

    public Address(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }
}
