package com.hdv.magiccoffee.models;

public class Header {
    int id;
    String image;

    public Header(int id, String image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }
}

