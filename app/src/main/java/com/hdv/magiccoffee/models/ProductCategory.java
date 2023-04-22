package com.hdv.magiccoffee.models;

public enum ProductCategory {
    COFFEE, TEA, FRUIT_JUICE, CAKE;

    public String getProductCategory() {

        switch (this) {
            case COFFEE:
                return "COFFEE";

            case TEA:
                return "TEA";

            case FRUIT_JUICE:
                return "FRUIT_JUICE";

            default:
                return "CAKE";
        }
    }
}
