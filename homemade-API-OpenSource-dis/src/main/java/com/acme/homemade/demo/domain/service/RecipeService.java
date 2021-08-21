package com.acme.homemade.demo.domain.service;

import com.acme.homemade.demo.domain.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface RecipeService {

    Recipe getRecipeById(Long recipeId);

    Page<Recipe> getAllRecipeByUserChefId(Long userChefId, Pageable pageable);

    Recipe getRecipeByTitle(String title);

    Page<Recipe> getAllRecipe(Pageable pageable);

    Recipe createRecipe(Long userChefId, Recipe recipe);

    Recipe updateRecipe(Long userChefId, Long recipeId ,Recipe recipe);

    ResponseEntity<?> deleteRecipe (Long userChefId, Long recipeId);
}
