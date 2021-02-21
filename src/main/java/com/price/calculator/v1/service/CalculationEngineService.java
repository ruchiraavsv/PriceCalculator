package com.price.calculator.v1.service;


import com.price.calculator.v1.dto.ItemCartDTO;
import com.price.calculator.v1.model.Item;

public interface CalculationEngineService {

    public float calculatePriceForUnitsOfItemsForPriceList(Item item, int count);
    public float calculatePriceForUnitsOfItems(Item item, int count);
    public float calculatePriceForCartonsOfItems(Item item, int count);
    public float buildTotalPriceForCart(ItemCartDTO itemCartDTO);
    public float[] pricesList(String itemName);

}
