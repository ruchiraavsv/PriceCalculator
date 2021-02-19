package com.price.calculator.v1.service;


public interface CalculationEngineService {

    public float calculatePriceForNumberOfItems(String itemName, int count);
    public float buildTotalPriceForCart(int horseShoeCount,int PenguinEarCount);
    public float[] pricesList(String itemName);
}
