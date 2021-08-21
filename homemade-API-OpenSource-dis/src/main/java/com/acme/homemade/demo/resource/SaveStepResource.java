package com.acme.homemade.demo.resource;

import com.sun.istack.NotNull;

public class SaveStepResource {
    private String text;
    private String image;


    public String getText() {
        return text;
    }

    public SaveStepResource setText(String text) {
        this.text = text;
        return this;
    }

    public String getImage() {
        return image;
    }

    public SaveStepResource setImage(String image) {
        this.image = image;
        return this;
    }
}
