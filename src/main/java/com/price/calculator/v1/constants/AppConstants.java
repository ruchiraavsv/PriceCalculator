package com.price.calculator.v1.constants;

public class AppConstants {

    public static final String HORSESHOE = "horseShoe";
    public static final String PENGUINEAR = "penguinEar";
    public static final int PRICE_LIST_SIZE = 50;
    public static final double SINGLE_ITEM_ADDITIONAL_EXPENSE_RATE = 1.3;
    public static final double MULTI_CART_DISCOUNT = 0.9;
    public static final int CART_COUNT_FOR_DISCOUNT = 3;


    private AppConstants() {
        throw new IllegalStateException("Utility class");
    }
}
