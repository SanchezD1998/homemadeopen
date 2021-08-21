package com.acme.homemade.demo.resource;

import java.util.Date;

public class MenuResource {
    private Long id;
    private Date dateOfRecipe;

    public Long getId() {
        return id;
    }

    public MenuResource setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getDateOfRecipe() {
        return dateOfRecipe;
    }

    public MenuResource setDateOfRecipe(Date dateOfRecipe) {
        this.dateOfRecipe = dateOfRecipe;
        return this;
    }
}
