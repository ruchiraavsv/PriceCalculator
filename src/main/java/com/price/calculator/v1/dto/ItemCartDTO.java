package com.price.calculator.v1.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class ItemCartDTO {
    private final int horseShoeUnits;
    private final int horseShoeCartons;
    private final int penguinEarUnits;
    private final int penguinEarCartons;

    @JsonCreator
    public ItemCartDTO(@JsonProperty("horseShoeUnits") int horseShoeUnits,
                       @JsonProperty("horseShoeCartons") int horseShoeCartons,
                       @JsonProperty("penguinEarUnits") int penguinEarUnits,
                       @JsonProperty("penguinEarCartons") int penguinEarCartons
                       ){
        super();
        this.horseShoeCartons=horseShoeCartons;
        this.horseShoeUnits = horseShoeUnits;
        this.penguinEarCartons=penguinEarCartons;
        this.penguinEarUnits=penguinEarUnits;
    }
}
