package com.hdv.magiccoffee.features.commondata;

import java.io.Serializable;

public class RedirectingData implements Serializable {
    Product product;
    String screen;
    int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    public RedirectingData() {
    }

    public RedirectingData(Product product, String screen) {
        this.product = product;
        this.screen = screen;
    }

    public RedirectingData(Product product, String screen, int index) {
        this.product = product;
        this.screen = screen;
        this.index = index;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }
}
