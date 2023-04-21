package com.hdv.magiccoffee.data.models;

public class Account {

    long id;
    String image;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String address;

    public Account(long id, String image, String firstName, String lastName, String email, String phoneNumber, String address) {
        this.id = id;
        this.image = image;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
}
