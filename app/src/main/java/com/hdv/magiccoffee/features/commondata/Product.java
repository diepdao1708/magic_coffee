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
    Topping topping = Topping.NONE;

    public double getPrice() {
        return quantity * (size.getPrice(cost) + topping.getPrice());
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Topping getTopping() {
        return topping;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
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

    public Product(int id, String image, String name, double cost, String description, int quantity, Size size, Topping topping) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.quantity = quantity;
        this.size = size;
        this.topping = topping;
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
