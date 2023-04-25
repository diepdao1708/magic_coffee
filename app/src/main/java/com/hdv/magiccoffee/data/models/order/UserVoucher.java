package com.hdv.magiccoffee.data.models.order;

import com.hdv.magiccoffee.models.User;
import com.hdv.magiccoffee.models.Voucher;

public class UserVoucher {
    int id;
    Voucher voucher;
    User user;
    boolean isUsed;

    public int getId() {
        return id;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public User getUser() {
        return user;
    }

    public boolean isUsed() {
        return isUsed;
    }
}
