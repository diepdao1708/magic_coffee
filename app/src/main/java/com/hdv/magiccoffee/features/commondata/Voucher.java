package com.hdv.magiccoffee.features.commondata;

import java.io.Serializable;

public class Voucher implements Serializable {
    int id;
    String image;
    String name;
    String quantity;
    String date;

    String description;

    public Voucher(int id, String image, String name, String quantity, String date, String description) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.quantity = quantity;
        this.date = date;
        this.description = description;
    }

    public Voucher() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
