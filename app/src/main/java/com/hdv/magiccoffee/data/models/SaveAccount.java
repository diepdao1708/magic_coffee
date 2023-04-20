package com.hdv.magiccoffee.data.models;

public class SaveAccount {
    // TODO
    public static Account account = new Account(
            "https://goo.gl/32YN2B",
            "Dao Bich Diep",
            "diep@gmail.com",
            "0353331111",
            "24 Ng. 217 Đ. Trần Phú, P. Văn Quán, Hà Đông, Hà Nội, Việt Nam."
    );
    public static String accessToken = "";

    public static void setPhoneNumber(String phoneNumber) {
        account.setPhoneNumber(phoneNumber);
    }
}
