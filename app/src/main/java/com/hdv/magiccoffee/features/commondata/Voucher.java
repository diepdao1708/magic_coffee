package com.hdv.magiccoffee.features.commondata;

public class Voucher {
    int id;
    String image;
    String name;
    String quantity;
    String date;

    public Voucher(int id, String image, String name, String quantity, String date) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.quantity = quantity;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }
}
