package com.acme.homemade.demo.resource;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

public class SaveRecipeResource {
    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    @Lob
    private String content;

    private String picture;

    public String getTitle() {
        return title;
    }

    public SaveRecipeResource setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveRecipeResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SaveRecipeResource setContent(String content) {
        this.content = content;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public SaveRecipeResource setPicture(String picture) {
        this.picture = picture;
        return this;
    }
}
