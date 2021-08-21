package com.acme.homemade.demo.controller;


import com.acme.homemade.demo.domain.model.Menu;
import com.acme.homemade.demo.domain.model.Publication;
import com.acme.homemade.demo.domain.service.MenuService;
import com.acme.homemade.demo.resource.MenuResource;
import com.acme.homemade.demo.resource.PublicationResource;
import com.acme.homemade.demo.resource.SaveMenuResource;
import com.acme.homemade.demo.resource.SavePublicationResource;
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
public class MenuController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MenuService menuService;

    @Operation(summary = "Get Menus", description = "Get All UserNoChef by userId", tags = {"menus"})
    @GetMapping("/menus/users/{userId}")
    public Page<MenuResource> getAllMenuByUserId(@PathVariable Long userId, Pageable pageable){
        Page<Menu> menuPage = menuService.getAllMenuByUserId(userId, pageable);
        List<MenuResource> resource= menuPage
                .getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resource, pageable, resource.size());
    }

    @Operation(summary = "Get menus", description = "Get All Menus", tags = {"menus"})
    @GetMapping("/menus")
    public Page<MenuResource> getAllMenus (Pageable pageable){
        Page<Menu> menuPage = menuService.getAllMenu(pageable);
        List<MenuResource> resource= menuPage
                .getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resource, pageable, resource.size());
    }
    @Operation(summary = "Create Menus", description = "Create a new menus", tags = {"menus"})
    @PostMapping("/menus/users/{userId}")
    public MenuResource createMenu(@PathVariable Long userId, @Valid @RequestBody SaveMenuResource resource){
        return convertToResource(menuService.createMenu(userId, convertToEntity(resource)));
    }
    @Operation(summary = "Update menus", description = "Update menus for given Id", tags = {"menus"})
    @PutMapping("/menus/{menuId}/users/{userId}")
    public MenuResource updatePublication(@PathVariable(value = "menuId") Long menuId,
                                                 @PathVariable(value = "userId") Long userId,
                                                 @Valid @RequestBody SaveMenuResource resource){
        return convertToResource(menuService.updateMenu(userId, menuId, convertToEntity(resource)));
    }

    @Operation(summary = "Delete menus", description = "Delete menus with given Id", tags = {"menus"})
    @DeleteMapping("/menus/{menuId}/users/{userId}")
    public ResponseEntity<?> deletePublication(@PathVariable(value = "menuId") Long menuId,
                                               @PathVariable(value = "userId") Long userId){
        return menuService.deleteMenu(userId, menuId);
    }

    private Menu convertToEntity(SaveMenuResource resource) {
        return mapper.map(resource, Menu.class);
    }


    private MenuResource convertToResource(Menu entity) {
        return mapper.map(entity, MenuResource.class);
    }


}
