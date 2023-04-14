package com.hdv.magiccoffee.features.commondata;

public enum Topping {
    ONE, TWO, THREE, FOUR, FIVE, NONE;

    public String getTopping() {

        switch (this) {
            case ONE:
                return "Kem Phô Mai Macchiato";

            case TWO:
                return "Shot Espesso";

            case THREE:
                return "Sốt Caramel";

            case FOUR:
                return "Thạch Cà Phê";

            case FIVE:
                return "Trân Châu Trắng";

            default:
                return null;
        }
    }

    public double getPrice() {
        if (this == Topping.NONE) {
            return 0f;
        }
        return 10f;
    }
}
