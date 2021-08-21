package com.acme.homemade.demo.domain.service;

import com.acme.homemade.demo.domain.model.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MenuService {
    Menu getMenuById(Long menuId);
    Page<Menu> getAllMenu (Pageable pageable);
    Page<Menu> getAllMenuByUserId(Long userId, Pageable pageable);
    Menu assignMenuRecipe(Long menuId, Long recipeId);
    Menu unassignMenuRecipe(Long menuId, Long recipeId);
    Menu createMenu( Long userId, Menu menu);
    Menu updateMenu(Long userId, Long menuId, Menu menu);
    ResponseEntity<?> deleteMenu(Long userId,Long menuId);
}
