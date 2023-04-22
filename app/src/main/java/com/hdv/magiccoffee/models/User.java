package com.hdv.magiccoffee.models;

import java.util.Date;

public class User {
    long id;
    String email;
    String password;
    String fullname;
    Date birthDate;
    String gender;
    String avatarLink;
    Date beginDate;
    String phoneNum;

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFullname() {
        return fullname;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public String getPhoneNum() {
        return phoneNum;
    }
}
