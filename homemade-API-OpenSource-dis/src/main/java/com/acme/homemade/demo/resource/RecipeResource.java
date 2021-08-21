package com.acme.homemade.demo.resource;


public class RecipeResource {
    private Long id;
    private String title;
    private String description;
    private String content;

    private String picture;

    public Long getId() {
        return id;
    }

    public RecipeResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public RecipeResource setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RecipeResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getContent() {
        return content;
    }

    public RecipeResource setContent(String content) {
        this.content = content;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public RecipeResource setPicture(String picture) {
        this.picture = picture;
        return this;
    }
}
