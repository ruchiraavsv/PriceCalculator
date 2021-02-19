package com.price.calculator.v1.model;

public abstract class Item {

    private int cartonSize;
    private int cartonPrice;

    public int getCartonSize() {
        return cartonSize;
    }

    public int getCartonPrice() {
        return cartonPrice;
    }

    public void setCartonSize(int cartonSize) {
        this.cartonSize = cartonSize;
    }

    public void setCartonPrice(int cartonPrice) {
        this.cartonPrice = cartonPrice;
    }
}
