package com.acme.homemade.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    @Lob
    private String content;

    private String picture;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userChef_id", nullable = false)
    @JsonIgnore
    private UserChef userChef;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "recipes")
    private List<Menu> menus;

    public Long getId() {
        return id;
    }

    public Recipe setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Recipe setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Recipe setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Recipe setContent(String content) {
        this.content = content;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public Recipe setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public UserChef getUserChef() {
        return userChef;
    }

    public Recipe setUserChef(UserChef userChef) {
        this.userChef = userChef;
        return this;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public Recipe setMenus(List<Menu> menus) {
        this.menus = menus;
        return this;
    }
}
