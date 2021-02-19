package com.price.calculator.v1.resource;

import com.price.calculator.v1.service.CalculationEngineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/priceCalculator")
public class PriceController {

    private final CalculationEngineService calculationEngineService;

    public PriceController(final CalculationEngineService calculationEngineService){
        this.calculationEngineService=calculationEngineService;
    }

    public ResponseEntity<float[]> getPriceList(String itemName){
        return new ResponseEntity<>(this.calculationEngineService.pricesList(itemName), HttpStatus.OK);
    }

    public ResponseEntity<Float> getBuiltPrice(int horseShoeCount,int PenguinEarCount){
        return new ResponseEntity<>(this.calculationEngineService.buildTotalPriceForCart(horseShoeCount,PenguinEarCount),HttpStatus.OK);
    }

}
