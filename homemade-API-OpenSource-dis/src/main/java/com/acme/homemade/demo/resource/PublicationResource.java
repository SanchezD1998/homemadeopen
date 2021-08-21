package com.acme.homemade.demo.resource;

import java.util.Date;

public class PublicationResource {
    private Long id;
    private Date date;
    private String text;
    private Integer likes;

    public Long getId() {
        return id;
    }

    public PublicationResource setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public PublicationResource setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getText() {
        return text;
    }

    public PublicationResource setText(String text) {
        this.text = text;
        return this;
    }

    public Integer getLikes() {
        return likes;
    }

    public PublicationResource setLikes(Integer likes) {
        this.likes = likes;
        return this;
    }
}
