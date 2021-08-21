package com.acme.homemade.demo.service;

import com.acme.homemade.demo.domain.model.*;
import com.acme.homemade.demo.domain.repository.*;
import com.acme.homemade.demo.domain.service.IngredientService;
import com.acme.homemade.demo.execption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Ingredient getIngredientById(Long ingredientId) {
        return ingredientRepository.findById(ingredientId).orElseThrow(() -> new ResourceNotFoundException("Ingredient", "Id", ingredientId));
    }

    @Override
    public Page<Ingredient> GetAllIngredient(Pageable pageable) {
        return ingredientRepository.findAll(pageable);
    }

    @Override
    public Page<Ingredient> GetAllIngredientByRecipeId(Long recipeId, Pageable pageable) {
        return null;
    }

    @Override
    public Ingredient createIngredient(Long recipeId, Ingredient ingredient) {
        return recipeRepository.findById(recipeId).map(recipe -> {
            ingredient.setRecipe(recipe);
            return ingredientRepository.save(ingredient);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "RecipeId","Id", recipeId));
    }
    @Override
    public Ingredient updateIngredient(Long recipeId, Long ingredientId, Ingredient ingredient) {
        if (!recipeRepository.existsById(recipeId))
            throw new ResourceNotFoundException("Recipe", "Id", recipeId);
        return ingredientRepository.findById(ingredientId).map(ingredient1 -> {
            ingredient1.setName(ingredient.getName());
            ingredient1.setQuantity(ingredient.getQuantity());
            return ingredientRepository.save(ingredient1);
        }).orElseThrow(() -> new ResourceNotFoundException("Ingredient", "Id", ingredientId));
    }
    @Override
    public ResponseEntity<?> deleteIngredient(Long recipeId, Long ingredientId) {
        if (!recipeRepository.existsById(recipeId))
            throw new ResourceNotFoundException("Recipe", "Id", recipeId);
        return ingredientRepository.findById(ingredientId).map(ingredient -> {
            ingredientRepository.delete(ingredient);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Recipe", "Id", ingredientId));
    }
}
