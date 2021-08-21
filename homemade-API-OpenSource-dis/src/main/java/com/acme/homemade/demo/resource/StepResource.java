package com.acme.homemade.demo.resource;

public class StepResource {
    private Long id;
    private String text;
    private String image;

    public Long getId() {
        return id;
    }

    public StepResource setId(Long id) {
        this.id = id;
        return this;
    }


    public String getText() {
        return text;
    }

    public StepResource setText(String text) {
        this.text = text;
        return this;
    }

    public String getImage() {
        return image;
    }

    public StepResource setImage(String image) {
        this.image = image;
        return this;
    }
}
