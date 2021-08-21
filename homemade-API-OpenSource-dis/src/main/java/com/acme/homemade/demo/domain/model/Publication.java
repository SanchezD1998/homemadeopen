package com.acme.homemade.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Publications")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotNull
    private Date date;

    private String text;

    private Integer likes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;


    public Long getId() {
        return id;
    }

    public Publication setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Publication setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getText() {
        return text;
    }

    public Publication setText(String text) {
        this.text = text;
        return this;
    }

    public Integer getLikes() {
        return likes;
    }

    public Publication setLikes(Integer likes) {
        this.likes = likes;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Publication setUser(User user) {
        this.user = user;
        return this;
    }
}
