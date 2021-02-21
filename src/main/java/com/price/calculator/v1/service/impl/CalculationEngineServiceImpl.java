package com.price.calculator.v1.service.impl;

import com.price.calculator.v1.constants.AppConstants;
import com.price.calculator.v1.model.HorseShoe;
import com.price.calculator.v1.model.Item;
import com.price.calculator.v1.model.PenguinEar;
import com.price.calculator.v1.service.CalculationEngineService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Service
public class CalculationEngineServiceImpl implements CalculationEngineService {

    private Map<String,Item> items;
    public CalculationEngineServiceImpl(){
        this.items=new HashMap<>();
        items.put(AppConstants.HORSESHOE,new HorseShoe());
        items.put(AppConstants.PENGUINEAR,new PenguinEar());
    }

    //using float does not cause precision problems here due to 175/20,825/5 and multiply 0.9,1.3
    @Override
    public float calculatePriceForNumberOfItems(String itemName,int count){
        Item item=this.items.get(itemName);
        float unitPrice=(float)item.getCartonPrice()/item.getCartonSize();
        int remainder=count%item.getCartonSize();
        int cartons=count/item.getCartonSize();
        float price=0;

        if(count<item.getCartonSize()){
            price+=(float) (unitPrice*(float) count * 1.3);
        }
        else if(count>=item.getCartonSize() && count<3*item.getCartonSize()){
            price+=(float) (cartons)*item.getCartonPrice();
            price+=(float) unitPrice* remainder * 1.3;
        }
        else{
            price+=(float) cartons*item.getCartonPrice()*0.9;
            price+=(float) unitPrice* remainder * 1.3;
        }
        return price;
    }

    @Override
    public float buildTotalPriceForCart(int horseShoeCount,int PenguinEarCount){
        return calculatePriceForNumberOfItems(AppConstants.PENGUINEAR,PenguinEarCount)+
                calculatePriceForNumberOfItems(AppConstants.HORSESHOE,horseShoeCount);
    }

    @Override
    public float[] pricesList(String itemName){
        float[] prices=new float[50];
        IntStream.rangeClosed(1,50).forEach(i->{prices[i-1]=this.calculatePriceForNumberOfItems(itemName,i);});
        int a= prices.length;
        return prices;
    }

}
