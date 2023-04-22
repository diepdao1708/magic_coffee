package com.hdv.magiccoffee.models;

import java.io.Serializable;

public class RedirectingData implements Serializable {
    OrderProduct orderProduct;
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

    public RedirectingData(OrderProduct orderProduct, String screen) {
        this.orderProduct = orderProduct;
        this.screen = screen;
    }

    public RedirectingData(OrderProduct orderProduct, String screen, int index) {
        this.orderProduct = orderProduct;
        this.screen = screen;
        this.index = index;
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }
}
