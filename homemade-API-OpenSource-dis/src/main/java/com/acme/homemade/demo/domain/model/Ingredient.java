package com.acme.homemade.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String unitOfMeasur;

    @NotNull
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recipe_id", nullable = false)
    @JsonIgnore
    private Recipe recipe;

    public Long getId() {
        return id;
    }

    public Ingredient setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Ingredient setName(String name) {
        this.name = name;
        return this;
    }

    public String getUnitOfMeasur() {
        return unitOfMeasur;
    }

    public Ingredient setUnitOfMeasur(String unitOfMeasur) {
        this.unitOfMeasur = unitOfMeasur;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Ingredient setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Ingredient setRecipe(Recipe recipe) {
        this.recipe = recipe;
        return this;
    }
}
