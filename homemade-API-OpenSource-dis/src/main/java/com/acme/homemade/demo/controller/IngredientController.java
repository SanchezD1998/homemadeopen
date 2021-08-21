package com.acme.homemade.demo.controller;


import com.acme.homemade.demo.domain.model.Ingredient;
import com.acme.homemade.demo.domain.model.Menu;
import com.acme.homemade.demo.domain.model.Message;
import com.acme.homemade.demo.domain.model.Recipe;
import com.acme.homemade.demo.domain.service.IngredientService;
import com.acme.homemade.demo.domain.service.MenuService;
import com.acme.homemade.demo.domain.service.RecipeService;
import com.acme.homemade.demo.resource.*;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class IngredientController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IngredientService ingredientService;

    @Operation(summary = "Get Ingredient", description = "Get All Ingredient by RecipeId", tags = {"ingredients"})
    @GetMapping("/ingredient/recipes/{recipeId}")
    public Page<IngredientResource> GetAllIngredientByRecipeId(@PathVariable Long recipeId, Pageable pageable){
        Page<Ingredient> ingredientPage = ingredientService.GetAllIngredientByRecipeId(recipeId, pageable);
        List<IngredientResource> resource= ingredientPage
                .getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resource, pageable, resource.size());
    }

    @Operation(summary = "Get ingredients", description = "Get All Ingredients", tags = {"ingredients"})
    @GetMapping("/ingredients")
    public Page<IngredientResource> GetAllIngredient (Pageable pageable){
        Page<Ingredient> ingredientPage = ingredientService.GetAllIngredient(pageable);
        List<IngredientResource> resource= ingredientPage
                .getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resource, pageable, resource.size());
    }
    @Operation(summary = "Create Ingredients", description = "Create a new ingredients", tags = {"ingredients"})
    @PostMapping("/ingredient/recipes/{recipeId}")
    public IngredientResource createIngredient(@PathVariable Long recipeId, @Valid @RequestBody SaveIngredientResource resource){
        return convertToResource(ingredientService.createIngredient(recipeId, convertToEntity(resource)));
    }
    @Operation(summary = "Update ingredients", description = "Update ingredients for given Id", tags = {"ingredients"})
    @PutMapping("/ingredients/{ingredientId}/recipes/{recipeId}")
    public IngredientResource updateIngredient(@PathVariable(value = "ingredientId") Long ingredientId,
                                          @PathVariable(value = "recipeId") Long recipeId,
                                          @Valid @RequestBody SaveIngredientResource resource){
        return convertToResource(ingredientService.updateIngredient(recipeId, ingredientId, convertToEntity(resource)));
    }

    @Operation(summary = "Delete ingredients", description = "Delete ingredients with given Id", tags = {"ingredients"})
    @DeleteMapping("/ingredients/{ingredientId}/recipes/{recipeId}")
    public ResponseEntity<?> deleteIngredient(@PathVariable(value = "ingredientId") Long ingredientId,
                                               @PathVariable(value = "recipeId") Long recipeId){
        return ingredientService.deleteIngredient(recipeId, ingredientId);
    }


    private Ingredient convertToEntity(SaveIngredientResource resource) {return mapper.map(resource, Ingredient.class);}
    private IngredientResource convertToResource(Ingredient entity) {return mapper.map(entity, IngredientResource.class);}
}
