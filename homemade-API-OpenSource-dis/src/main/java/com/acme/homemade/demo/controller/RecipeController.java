package com.acme.homemade.demo.controller;


import com.acme.homemade.demo.domain.model.Comment;
import com.acme.homemade.demo.domain.model.Recipe;
import com.acme.homemade.demo.domain.service.RecipeService;
import com.acme.homemade.demo.resource.CommentResource;
import com.acme.homemade.demo.resource.RecipeResource;
import com.acme.homemade.demo.resource.SaveRecipeResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
public class RecipeController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RecipeService recipeService;

    @Operation(summary = "Get Recipes", description = "Get All Recipes by Pages", tags = {"Recipes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Recipes returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/recipes")
    public Page<RecipeResource> getAllRecipes(Pageable pageable) {
        Page<Recipe> recipePage = recipeService.getAllRecipe(pageable);
        List<RecipeResource> resources = recipePage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Recipe By Id", description = "Get Recipes for given Id", tags = {"Recipes"})
    @GetMapping("/recipes/{id}")
    public RecipeResource getRecipeById(
            @PathVariable(name = "id") Long recipeId) {
        return convertToResource(recipeService.getRecipeById(recipeId));
    }

    @Operation(summary = "Get Recipe By Id", description = "Get Recipes for given Id", tags = {"Recipes"})
    @GetMapping("/recipes/{title}")
    public RecipeResource getTagById(
            @PathVariable(name = "title") String title) {
        return convertToResource(recipeService.getRecipeByTitle(title));
    }

    @Operation(summary = "Get Recipes", description = "Get All Recipes by userChefId", tags = {"Recipes"})
    @GetMapping("/recipe/userChef/{userChefId}")
    public Page<RecipeResource> getAllRecipeByUserChefId(
            @PathVariable Long userChefId, Pageable pageable) {
        Page<Recipe> recipePage = recipeService.getAllRecipeByUserChefId(userChefId, pageable);
        List<RecipeResource> resources = recipePage
                .getContent()
                .stream().map(
                        this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }


    @Operation(summary = "Create recipe", description = "Create a new recipe", tags = {"Recipes"})
    @PostMapping("/users/{userId}/recipes")
    public RecipeResource createRecipe(
            @PathVariable(value = "userId") Long userId,
            @Valid @RequestBody SaveRecipeResource resource) {
        return convertToResource(recipeService.createRecipe(userId, convertToEntity(resource)));
    }

    @Operation(summary = "Update Recipe", description = "Update Recipe for given Id", tags = {"Recipes"})
    @PutMapping("/recipes/{recipeId}/users/{userId}")
    public RecipeResource updateRecipe(@PathVariable(value = "recipeId") Long recipeId,
                                       @PathVariable(value = "userId") Long userId,
                                       @Valid @RequestBody SaveRecipeResource resource){
        return convertToResource(recipeService.updateRecipe(userId, recipeId, convertToEntity(resource)));
    }

    @Operation(summary = "Delete Recipe", description = "Delete recipe with given Id", tags = {"Recipes"})
    @DeleteMapping("/recipes/{recipeId}/users/{userId}")
    public ResponseEntity<?> deleteRecipe(@PathVariable(value = "recipeId") Long recipeId,
                                               @PathVariable(value = "userId") Long userId){
        return recipeService.deleteRecipe(userId, recipeId);
    }

    private Recipe convertToEntity(SaveRecipeResource resource) {
        return mapper.map(resource, Recipe.class);
    }


    private RecipeResource convertToResource(Recipe entity) {
        return mapper.map(entity, RecipeResource.class);
    }

}
