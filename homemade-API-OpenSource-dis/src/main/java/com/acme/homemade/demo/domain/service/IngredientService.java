package com.acme.homemade.demo.domain.service;

import com.acme.homemade.demo.domain.model.Ingredient;
import com.acme.homemade.demo.domain.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IngredientService {

    Ingredient getIngredientById(Long ingredientId);
    Page<Ingredient> GetAllIngredient(Pageable pageable);
    Page<Ingredient> GetAllIngredientByRecipeId(Long recipeId,Pageable pageable);
    Ingredient createIngredient(Long recipeId, Ingredient ingredient);
    Ingredient updateIngredient(Long recipeId, Long ingredientId ,Ingredient ingredient);
    ResponseEntity<?> deleteIngredient (Long recipeId, Long ingredientId);
}
