package com.hdv.magiccoffee.models;

import java.io.Serializable;

public class Voucher implements Serializable {
    int id;
    String image;
    String name;
    String date;
    String description;
    float discount;
    String voucherType;

    public Voucher(int id, String image, String name, String date, String description, float discount, String voucherType) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.date = date;
        this.description = description;
        this.discount = discount;
        this.voucherType = voucherType;
    }

    public Voucher() {
    }

    public String getDescription() {
        return description;
    }

    public float getDiscount() {
        return discount;
    }

    public String getVoucherType() {
        return voucherType;
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

    public String getDate() {
        return date;
    }
}
