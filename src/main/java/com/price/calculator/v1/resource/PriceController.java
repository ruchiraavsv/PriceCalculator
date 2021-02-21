package com.price.calculator.v1.resource;

import com.price.calculator.v1.dto.ItemCartDTO;
import com.price.calculator.v1.service.CalculationEngineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/price-calculator")
public class PriceController {

    private final CalculationEngineService calculationEngineService;

    public PriceController(final CalculationEngineService calculationEngineService) {
        this.calculationEngineService = calculationEngineService;
    }

    @GetMapping("price-list/{itemName}")
    public ResponseEntity<float[]> getPriceList(@PathVariable String itemName) {
        return new ResponseEntity<>(this.calculationEngineService.pricesList(itemName), HttpStatus.OK);
    }

    @PostMapping("price-total")
    public ResponseEntity<Float> getBuiltPrice(@RequestBody ItemCartDTO itemCartDTO) {
        return new ResponseEntity<>(this.calculationEngineService.buildTotalPriceForCart(itemCartDTO), HttpStatus.OK);
    }

}
