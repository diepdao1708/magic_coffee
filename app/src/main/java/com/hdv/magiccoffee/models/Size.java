package com.hdv.magiccoffee.models;

import android.annotation.SuppressLint;

public enum Size {
    LARGE, MEDIUM, SMALL;

    @SuppressLint("DefaultLocale")
    public String getSize(double cost) {

        switch (this) {
            case SMALL:
                return String.format("Nhỏ - %.3fđ", cost - 5f);

            case MEDIUM:
                return String.format("Vừa - %.3fđ", cost);

            case LARGE:
                return String.format("Lớn - %.3fđ", cost + 5f);

            default:
                return null;
        }
    }

    public String getSize() {

        switch (this) {
            case SMALL:
                return "SMALL";

            case MEDIUM:
                return "MEDIUM";

            case LARGE:
                return "LARGE";

            default:
                return null;
        }
    }

    public double getPrice(double cost) {

        switch (this) {
            case SMALL:
                return cost - 5f;

            case MEDIUM:
                return cost;

            case LARGE:
                return cost + 5f;

            default:
                return 0;
        }
    }
}