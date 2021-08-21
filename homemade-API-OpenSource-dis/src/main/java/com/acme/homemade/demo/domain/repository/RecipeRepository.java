package com.acme.homemade.demo.domain.repository;


import com.acme.homemade.demo.domain.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Page<Recipe> findById(Long Id, Pageable pageable);
    Page<Recipe> findByUserChefId(Long userChefId, Pageable pageable);
    Optional<Recipe> findByIdAndUserChefId(Long Id,Long userChefId);
    public Optional<Recipe> findByTitle(String title);

}
