package com.hdv.magiccoffee.features.commondata;

public enum Size {
    LARGE, MEDIUM, SMALL;

    public String getSize() {

        switch (this) {
            case SMALL:
                return "Nhỏ";

            case MEDIUM:
                return "Vừa";

            case LARGE:
                return "Lớn";

            default:
                return null;
        }
    }
}