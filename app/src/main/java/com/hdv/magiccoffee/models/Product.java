package com.hdv.magiccoffee.models;

public class Product {
    String id;
    String name;
    double cost;
    String description;
    String image;

    String productCategory;

    public Product(String name, double cost, String description, String image, String productCategory) {
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.image = image;
        this.productCategory = productCategory;
    }

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

    public String getImage() {
        return image;
    }

    public String getProductCategory() {
        return productCategory;
    }
}
