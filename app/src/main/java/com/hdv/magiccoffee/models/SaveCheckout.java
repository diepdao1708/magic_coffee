package com.hdv.magiccoffee.models;

import java.util.ArrayList;

public class SaveCheckout {

    public static ArrayList<OrderProduct> orderProducts = new ArrayList<>();
    private static final double shippingPrice = 18f;
    public static Voucher voucher;
    public static Address address = SaveAccount.address.isEmpty() ? null : SaveAccount.address.get(0);
    public static String name = SaveAccount.firstName == null ? null : SaveAccount.lastName + " " + SaveAccount.firstName;
    public static String phoneNumber = SaveAccount.phoneNumber == null ? null : SaveAccount.phoneNumber;
    public static MethodPayment methodPayment = MethodPayment.MONEY;

    public static void addProduct(OrderProduct orderProduct) {
        orderProducts.add(orderProduct);
    }

    public static void updateProduct(OrderProduct orderProduct, int index) {
        orderProducts.set(index, orderProduct);
    }

    public static void deleteProduct(int index) {
        orderProducts.remove(index);
    }

    public static double totalPrice() {
        double total = 0f;
        for (OrderProduct orderProduct : orderProducts) {
            total += orderProduct.getPrice();
        }
        return total;
    }

    public static double checkoutPrice() {
        if (orderProducts.isEmpty())
            return 0;
        if (voucher != null) {
            if (voucher.getVoucherType().equals("SHIPPING")) {
                double s = shippingPrice - voucher.getDiscount();
                return s > 0 ? totalPrice() + s : totalPrice();
            } else {
                return totalPrice() + shippingPrice - voucher.getDiscount();
            }
        }
        return totalPrice() + shippingPrice;
    }

    public static double getShippingPrice() {
        return shippingPrice;
    }

    public static void deleteAllProduct() {
        orderProducts.clear();
    }
}
