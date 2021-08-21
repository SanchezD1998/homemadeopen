package com.acme.homemade.demo;

import com.acme.homemade.demo.domain.model.Recipe;
import com.acme.homemade.demo.domain.repository.RecipeRepository;
import com.acme.homemade.demo.domain.repository.UserChefRepository;
import com.acme.homemade.demo.domain.service.RecipeService;
import com.acme.homemade.demo.execption.ResourceNotFoundException;
import com.acme.homemade.demo.service.RecipeServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class RecipeServiceImpIntegrationTest {

    @MockBean
    private RecipeRepository recipeRepository;

    @MockBean
    private UserChefRepository userChefRepository;

    @Autowired
    private RecipeService recipeService;

    @TestConfiguration
    static class PostServiceImplTestConfiguration {
        @Bean
        public RecipeService recipeService() {
            return new RecipeServiceImpl();
        }
    }

    @Test
    @DisplayName("When Get Recipe By Id With Valid Id Then Returns Recipe")
    public void whenGetRecipeByIdWithValidIdThenReturnsRecipe() {
        // Arrange
        String title = "Rocoto relleno";
        String description = "El mejor rocoto relleno de todo chincha";
        String content = "the best roto relleno";
        Recipe recipe = new Recipe();
        recipe.setId(10L);
        recipe.setTitle(title);
        recipe.setDescription(description);
        recipe.setContent(content);
        //given(postRepository.findByTitle(post.getTitle()))
        //        .willReturn(Optional.of(post));
        when(recipeRepository.findById(10L)).thenReturn(Optional.of(recipe));
        // Act
        Recipe foundRecipe = recipeService.getRecipeById(10L);

        // Assert
        assertThat(foundRecipe.getId()).isEqualTo(10L);
    }
    @Test
    @DisplayName("When Get Recipe By Id With Invalid Id Then Returns ResourceNotFoundException")
    public void whenGetRecipeByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 15L;
        String title = "Rocoto relleno";
        String template = "Resource %s not found for %s with value %s";
        when(recipeRepository.findById(id)).thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Recipe", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> {
            Recipe recipe = recipeService.getRecipeById(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }
}
