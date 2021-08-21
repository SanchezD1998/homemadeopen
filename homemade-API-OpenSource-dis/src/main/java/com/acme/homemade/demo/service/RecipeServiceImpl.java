package com.acme.homemade.demo.service;

import com.acme.homemade.demo.domain.model.Recipe;
import com.acme.homemade.demo.domain.repository.RecipeRepository;
import com.acme.homemade.demo.domain.repository.UserChefRepository;
import com.acme.homemade.demo.domain.service.RecipeService;
import com.acme.homemade.demo.execption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserChefRepository chefRepository;
    @Override
    public Recipe getRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));
    }

    @Override
    public Page<Recipe> getAllRecipeByUserChefId(Long userChefId, Pageable pageable) {
        return recipeRepository.findByUserChefId(userChefId,pageable);
    }

    @Override
    public Recipe getRecipeByTitle(String title) {
        return recipeRepository.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "Title", title));
    }

    @Override
    public Page<Recipe> getAllRecipe(Pageable pageable) {
        return recipeRepository.findAll(pageable);
    }

    @Override
    public Recipe createRecipe(Long userChefId, Recipe recipe) {
        return chefRepository.findById(userChefId).map(userChef -> {
            recipe.setUserChef(userChef);
            return recipeRepository.save(recipe);
        }).orElseThrow(()->new ResourceNotFoundException("UserChefId","Id",userChefId));
    }

    @Override
    public Recipe updateRecipe(Long userChefId, Long recipeId, Recipe recipe) {
        if(!chefRepository.existsById(userChefId))
            throw new ResourceNotFoundException("UserChef","Id",userChefId);
        return recipeRepository.findById(recipeId).map(recipe1 -> {
            recipe1.setContent(recipe.getContent());
            recipe1.setDescription(recipe.getDescription());
            return recipeRepository.save(recipe1);
        }).orElseThrow(()->new ResourceNotFoundException("Recipe", "Id",recipeId));
    }

    @Override
    public ResponseEntity<?> deleteRecipe(Long userChefId, Long recipeId) {
        if(!chefRepository.existsById(userChefId))
            throw new ResourceNotFoundException("UserChef","Id", userChefId);
        return recipeRepository.findById(userChefId).map(recipe -> {
            recipeRepository.delete(recipe);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));
    }
}
