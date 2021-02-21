package com.price.calculator.v1.service;

import com.price.calculator.v1.constants.AppConstants;
import com.price.calculator.v1.constants.TestConstants;
import com.price.calculator.v1.model.Item;
import com.price.calculator.v1.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ServiceTestConfig.class)
class CalculationEngineServiceTest {

    @Autowired
    CalculationEngineService calculationEngineService;

    @Autowired
    ItemRepository itemRepository;



    @BeforeEach
    public void setUp() {
        Mockito.when(itemRepository.findByName(AppConstants.PENGUINEAR))
                .thenReturn(Optional.of(new Item(1, AppConstants.PENGUINEAR, TestConstants.penguinEar_carton_size, TestConstants.penguinEar_carton_price)));
        Mockito.when(itemRepository.findByName(AppConstants.HORSESHOE))
                .thenReturn(Optional.of(new Item(1, AppConstants.HORSESHOE, TestConstants.horseShoe_carton_size, TestConstants.horseShoe_carton_price)));
    }

    @Test
    void testCalculatePriceForZeroUnits() {
        Assertions.assertEquals(TestConstants.penguinEar_0_price, calculationEngineService.calculatePriceForUnitsOfItems(new Item(1,AppConstants.PENGUINEAR,TestConstants.penguinEar_carton_size,TestConstants.penguinEar_carton_price), TestConstants.penguinEar_0));
    }

    @Test
    void testCalculatePriceForUnits() {
        Assertions.assertEquals(TestConstants.penguinEar_8_price, calculationEngineService.calculatePriceForUnitsOfItems(new Item(1,AppConstants.PENGUINEAR,TestConstants.penguinEar_carton_size,TestConstants.penguinEar_carton_price), TestConstants.penguinEar_8));

    }

    @Test
    void testCalculatePriceForZeroCartons() {
        Assertions.assertEquals(TestConstants.penguinEar_0_price, calculationEngineService.calculatePriceForUnitsOfItems(new Item(1,AppConstants.PENGUINEAR,TestConstants.penguinEar_carton_size,TestConstants.penguinEar_carton_price), TestConstants.penguinEar_0));
    }

    @Test
    void testCalculatePriceForCartonsLessThanDiscountMargin() {
        Assertions.assertEquals(TestConstants.penguinEar_20_price, calculationEngineService.calculatePriceForCartonsOfItems(new Item(1,AppConstants.PENGUINEAR,TestConstants.penguinEar_carton_size,TestConstants.penguinEar_carton_price), 1));

    }

    @Test
    void testCalculatePriceForCartonsGreaterThanOrEqualToDiscountMargin() {
        Assertions.assertEquals(TestConstants.penguinEar_60_price, calculationEngineService.calculatePriceForCartonsOfItems(new Item(1,AppConstants.PENGUINEAR,TestConstants.penguinEar_carton_size,TestConstants.penguinEar_carton_price), 3));

    }


    @Test
    void testGetPriceLisForItem() {
        float[] pricesListPenguinEar = calculationEngineService.pricesList(AppConstants.PENGUINEAR);
        float[] pricesListHorseShoe = calculationEngineService.pricesList(AppConstants.HORSESHOE);

        Assertions.assertEquals(50, pricesListPenguinEar.length);
        Assertions.assertEquals(50, pricesListHorseShoe.length);

        Assertions.assertEquals(TestConstants.penguinEar_8_price,pricesListPenguinEar[TestConstants.penguinEar_8 - 1] );
        Assertions.assertEquals(TestConstants.horseShoe_18_price,pricesListHorseShoe[TestConstants.horseShoe_18 - 1]);
    }
}
