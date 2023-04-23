package com.hdv.magiccoffee.models;

public enum MethodPayment {
    MONEY, PAYPAL;

    public String getMethodPayment() {
        if (this == MethodPayment.PAYPAL) {
            return "Paypal";
        }
        return "Tiền mặt ";
    }
}
