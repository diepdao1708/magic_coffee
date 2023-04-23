package com.hdv.magiccoffee.models;

import java.io.Serializable;

public class OrderProduct implements Serializable {

    String productId;
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

    public OrderProduct() {
    }

    public String getProductId() {
        return productId;
    }

    public OrderProduct(String id, String image, String name, double cost, String description) {
        this.productId = id;
        this.image = image;
        this.name = name;
        this.cost = cost;
        this.description = description;
    }

    public OrderProduct(String id, String image, String name, double cost, String description, int quantity, Size size, Topping topping) {
        this.productId = id;
        this.image = image;
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.quantity = quantity;
        this.size = size;
        this.topping = topping;
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
