package com.acme.homemade.demo.resource;

import java.util.Date;

public class SaveMenuResource {
    private Date dateOfRecipe;

    public Date getDateOfRecipe() {
        return dateOfRecipe;
    }

    public SaveMenuResource setDateOfRecipe(Date dateOfRecipe) {
        this.dateOfRecipe = dateOfRecipe;
        return this;
    }
}
