package com.acme.homemade.demo.resource;

import javax.validation.constraints.NotNull;

import java.util.Date;

public class SaveIngredientResource {
    private String name;
    private String unitOfMeasur;
    private Long quantity;

    public String getName() {
        return name;
    }

    public SaveIngredientResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getUnitOfMeasur() {
        return unitOfMeasur;
    }

    public SaveIngredientResource setUnitOfMeasur(String unitOfMeasur) {
        this.unitOfMeasur = unitOfMeasur;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public SaveIngredientResource setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }
}
