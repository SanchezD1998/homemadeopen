package com.acme.homemade.demo.resource;

import com.sun.istack.NotNull;

import java.util.Date;

public class SaveCommentResource {

    private Date date;
    private String text;
    private Integer likes;

    public Date getDate() {
        return date;
    }

    public SaveCommentResource setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getText() {
        return text;
    }

    public SaveCommentResource setText(String text) {
        this.text = text;
        return this;
    }

    public Integer getLikes() {
        return likes;
    }

    public SaveCommentResource setLikes(Integer likes) {
        this.likes = likes;
        return this;
    }
}
