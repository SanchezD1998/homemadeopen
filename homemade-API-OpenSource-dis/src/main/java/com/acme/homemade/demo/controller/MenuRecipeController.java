package com.acme.homemade.demo.controller;

import com.acme.homemade.demo.domain.model.Menu;
import com.acme.homemade.demo.domain.service.MenuService;
import com.acme.homemade.demo.resource.MenuResource;
import com.acme.homemade.demo.resource.SaveMenuResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MenuRecipeController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MenuService menuService;

    @Operation(summary = "Assign Tag to Post", description = "Establishes association between menu and recipe", tags = {"menus"})
    @PostMapping("/recipes/{recipeId}/menus/{menuId}")
    public MenuResource assignMenuRecipe(
            @PathVariable(name = "recipeId") Long recipeId,
            @PathVariable(name = "menuId") Long menuId) {
        return convertToResource(menuService.assignMenuRecipe(menuId, recipeId));
    }

    @Operation(summary = "Remove assignment between Tag to Post", description = "Ends association between menu and recipe", tags = {"menus"})
    @DeleteMapping("/recipes/{recipeId}/menus/{menuId}")
    public MenuResource unassignMenuRecipe(
            @PathVariable(name = "recipeId") Long recipeId,
            @PathVariable(name = "menuId") Long menuId) {
        return convertToResource(menuService.unassignMenuRecipe(menuId, recipeId));
    }

    private Menu convertToEntity(SaveMenuResource resource) {
        return mapper.map(resource, Menu.class);
    }


    private MenuResource convertToResource(Menu entity) {
        return mapper.map(entity, MenuResource.class);
    }
}
