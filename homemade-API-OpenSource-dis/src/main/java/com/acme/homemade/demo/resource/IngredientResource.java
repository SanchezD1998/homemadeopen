package com.acme.homemade.demo.resource;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class IngredientResource {
    private Long id;
    private String name;
    private String unitOfMeasur;
    private Long quantity;

    public Long getId() {
        return id;
    }

    public IngredientResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public IngredientResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getUnitOfMeasur() {
        return unitOfMeasur;
    }

    public IngredientResource setUnitOfMeasur(String unitOfMeasur) {
        this.unitOfMeasur = unitOfMeasur;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public IngredientResource setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }
}
