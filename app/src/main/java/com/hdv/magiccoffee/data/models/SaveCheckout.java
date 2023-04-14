package com.hdv.magiccoffee.data.models;

import com.hdv.magiccoffee.features.commondata.Product;

import java.util.ArrayList;

public class SaveCheckout {

    public static ArrayList<Product> products = new ArrayList<>();
    private static final double shippingPrice = 18f;

    public static void addProduct(Product product) {
        products.add(product);
    }

    public static void updateProduct(Product product, int index) {
        products.set(index, product);
    }

    public static void deleteProduct(int index) {
        products.remove(index);
    }

    public static double totalPrice() {
        double total = 0f;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public static double checkoutPrice() {
        return totalPrice() + shippingPrice;
    }

    public static double getShippingPrice() {
        return shippingPrice;
    }
}
