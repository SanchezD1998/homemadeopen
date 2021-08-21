package com.acme.homemade.demo.service;

import com.acme.homemade.demo.domain.model.*;
import com.acme.homemade.demo.domain.repository.*;
import com.acme.homemade.demo.domain.service.MenuService;
import com.acme.homemade.demo.execption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserNoChefRepository userNoChefRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Menu getMenuById(Long menuId) {
        return menuRepository.findById(menuId).orElseThrow(() -> new ResourceNotFoundException("Menu", "Id", menuId));
    }

    @Override
    public Page<Menu> getAllMenu(Pageable pageable) {
        return menuRepository.findAll(pageable);
    }

    @Override
    public Page<Menu> getAllMenuByUserId(Long userId, Pageable pageable) {
        return menuRepository.findByUserId(userId, pageable);
    }

    @Override
    public Menu assignMenuRecipe(Long menuId, Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User", "Id", recipeId));
        return menuRepository.findById(menuId).map(
                menu -> menuRepository.save(menu.tagWith(recipe)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Chat", "Id", menuId));
    }

    @Override
    public Menu unassignMenuRecipe(Long menuId, Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Recipe", "Id", recipeId));
        return menuRepository.findById(menuId).map(
                chat -> menuRepository.save(chat.unTagWith(recipe)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Menu", "Id", menuId));
    }

    @Override
    public Menu createMenu( Long userId, Menu menu) {
        return userNoChefRepository.findById(userId).map( userNoChef -> {
            menu.setUser(userNoChef);
            return menuRepository.save(menu);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "UserId", "Id", userId));
    }

    @Override
    public Menu updateMenu(Long userId, Long menuId, Menu menu) {
        if (!userNoChefRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);
        return menuRepository.findById(menuId).map(menu1 -> {
            menu1.setDateOfRecipe(menu.getDateOfRecipe());
            return menuRepository.save(menu1);
        }).orElseThrow(() -> new ResourceNotFoundException("Menu", "Id", menuId));
    }

    @Override
    public ResponseEntity<?> deleteMenu(Long userId,Long menuId) {
        if (!userNoChefRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);
        return menuRepository.findById(menuId).map(menu -> {
            menuRepository.delete(menu);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Menu", "Id", menuId));
    }
}
