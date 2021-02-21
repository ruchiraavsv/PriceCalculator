package com.price.calculator.v1.service.impl;

import com.price.calculator.v1.constants.AppConstants;
import com.price.calculator.v1.dto.ItemCartDTO;
import com.price.calculator.v1.model.Item;
import com.price.calculator.v1.repository.ItemRepository;
import com.price.calculator.v1.service.CalculationEngineService;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

import static com.price.calculator.v1.constants.AppConstants.*;

@Service
public class CalculationEngineServiceImpl implements CalculationEngineService {

    private final ItemRepository itemRepository;

    public CalculationEngineServiceImpl(final ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public float calculatePriceForUnitsOfItemsForPriceList(Item item, int count) {
        float unitPrice = (float) item.getCartonPrice() / item.getCartonSize();
        int remainder = count % item.getCartonSize();
        int cartons = count / item.getCartonSize();
        return calculatePriceForUnitsOfItems(item, remainder) + calculatePriceForCartonsOfItems(item, cartons);
    }

    @Override
    public float calculatePriceForUnitsOfItems(Item item, int count) {
        float unitPrice = (float) item.getCartonPrice() / item.getCartonSize();
        return (float) (unitPrice * (float) count * 1.3);

    }

    @Override
    public float calculatePriceForCartonsOfItems(Item item, int count) {
        if (count >= CART_COUNT_FOR_DISCOUNT) {
            return (float) (item.getCartonPrice() * count * MULTI_CART_DISCOUNT);
        } else return (float) (item.getCartonPrice() * count);
    }

    @Override
    public float buildTotalPriceForCart(ItemCartDTO itemCartDTO) {
        Item horseShoe = itemRepository.findByName(HORSESHOE).get();
        Item penguinEar = itemRepository.findByName(PENGUINEAR).get();
        return calculatePriceForUnitsOfItems(horseShoe, itemCartDTO.getHorseShoeUnits()) +
                calculatePriceForUnitsOfItems(penguinEar, itemCartDTO.getPenguinEarUnits()) +
                calculatePriceForCartonsOfItems(horseShoe, itemCartDTO.getHorseShoeCartons()) +
                calculatePriceForCartonsOfItems(penguinEar, itemCartDTO.getPenguinEarCartons());
    }

    @Override
    public float[] pricesList(String itemName) {
        Item item = itemRepository.findByName(itemName).get();
        float[] prices = new float[AppConstants.PRICE_LIST_SIZE];
        IntStream.rangeClosed(1, AppConstants.PRICE_LIST_SIZE).forEach(i ->
                prices[i - 1] = this.calculatePriceForUnitsOfItemsForPriceList(item, i)
        );
        return prices;
    }

}
