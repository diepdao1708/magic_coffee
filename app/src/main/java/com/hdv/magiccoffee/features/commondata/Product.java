package com.hdv.magiccoffee.features.commondata;

import java.io.Serializable;

public class Product implements Serializable {
    int id;
    String image;
    String name;
    double cost;
    String description;
    int quantity = 1;
    Size size = Size.MEDIUM;

    public double getPrice() {
        if (size == Size.MEDIUM)
            return quantity * cost;
        if (size == Size.LARGE)
            return quantity * (cost - 5f);
        return quantity * (cost + 5f);
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product() {
    }

    public Product(int id, String image, String name, double cost, String description) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.cost = cost;
        this.description = description;
    }

    public Product(int id, String image, String name, double cost, String description, int quantity, Size size) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.quantity = quantity;
        this.size = size;
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

    public double getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }
}
