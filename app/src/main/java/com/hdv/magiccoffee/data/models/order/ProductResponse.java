package com.hdv.magiccoffee.data.models.order;

public class ProductResponse {
    String id;
    String name;
    double cost;
    String description;
    String imageLink;
    String productCategory;

    public String getId() {
        return id;
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

    public String getImageLink() {
        return imageLink;
    }

    public String getProductCategory() {
        return productCategory;
    }
}
