package com.acme.homemade.demo;

import com.acme.homemade.demo.domain.model.Ingredient;
import com.acme.homemade.demo.domain.repository.IngredientRepository;
import com.acme.homemade.demo.domain.repository.RecipeRepository;
import com.acme.homemade.demo.domain.service.IngredientService;
import com.acme.homemade.demo.execption.ResourceNotFoundException;
import com.acme.homemade.demo.service.IngredientServiceImpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class IngredientServiceImpIntegrationTest {

    @MockBean
    private IngredientRepository ingredientRepository;
    @MockBean
    private RecipeRepository recipeRepository;
    @Autowired
    private IngredientService ingredientService;

    @TestConfiguration
    static class  IngredientServiceImpTestConfiguration {
        @Bean
        public IngredientService ingredientService(){
            return new IngredientServiceImpl();
        }
    }

    @Test
    @DisplayName("When Get Ingredient By Id With Valid Id Then Returns Menu")
    public void whenGetIngredientByIdWithValidIdThenReturnsPublication() throws ParseException {
        //Arrange
        String title = "Papa amarilla";
        Ingredient ingredient = new Ingredient();
        ingredient.setId(5L);
        ingredient.setName(title);
        ingredient.setQuantity(10L);
        when(ingredientRepository.findById(5L)).thenReturn(Optional.of(ingredient));
        //Act
        Ingredient foundIngredient = ingredientService.getIngredientById(5L);

        //Assert
        assertThat(foundIngredient.getId()).isEqualTo(5L);
    }

    @Test
    @DisplayName("When GetIngredientId With Invalid Id Then Returns ResourceNotFoundException")
    public void whenGetIngredientByIdWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        Long id = 5L;
        String template = "Resource %s not found for %s with value %s";
        when(ingredientRepository.findById(id)).thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Ingredient", "Id", id);

        //Act
        Throwable exception = catchThrowable(()->{
            Ingredient ingredient = ingredientService.getIngredientById(id);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class).hasMessage(expectedMessage);
    }
}
