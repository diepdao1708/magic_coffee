package com.hdv.magiccoffee.data.models.order;

public class OrderProductResponse {
    private long id;

    private ProductResponse product;

    private String size;

    private String topping;

    private int quantity;

    public long getId() {
        return id;
    }

    public ProductResponse getProduct() {
        return product;
    }

    public String getSize() {
        return size;
    }

    public String getTopping() {
        return topping;
    }

    public int getQuantity() {
        return quantity;
    }
}
