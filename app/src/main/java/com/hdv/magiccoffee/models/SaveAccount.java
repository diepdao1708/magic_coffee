package com.hdv.magiccoffee.models;

import java.util.ArrayList;
import java.util.List;

public class SaveAccount {
    public static long id;
    public static String image;
    public static String firstName;
    public static String lastName;
    public static String email;
    public static String phoneNumber;
    public static List<Address> address = new ArrayList<>();
    public static String accessToken = "";
    public static List<Voucher> vouchers = new ArrayList<>();
}
