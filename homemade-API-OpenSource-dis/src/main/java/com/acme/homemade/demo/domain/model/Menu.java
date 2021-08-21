package com.acme.homemade.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Menus")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateOfRecipe;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "menus_recipes",
            joinColumns = {@JoinColumn(name = "menu_id")},
            inverseJoinColumns = {@JoinColumn(name = "recipe_id")})
    private List<Recipe> recipes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    public boolean isTaggedWith(Recipe recipe) {
        return this.getRecipes().contains(recipe);
    }

    public Menu tagWith(Recipe recipe) {
        if (!this.isTaggedWith(recipe))
            this.getRecipes().add(recipe);
        return this;
    }

    public Menu unTagWith(Recipe recipe) {
        if (this.isTaggedWith(recipe))
            this.getRecipes().remove(recipe);
        return this;
    }

    public Long getId() {
        return id;
    }

    public Menu setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getDateOfRecipe() {
        return dateOfRecipe;
    }

    public Menu setDateOfRecipe(Date dateOfRecipe) {
        this.dateOfRecipe = dateOfRecipe;
        return this;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public Menu setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Menu setUser(User user) {
        this.user = user;
        return this;
    }
}
