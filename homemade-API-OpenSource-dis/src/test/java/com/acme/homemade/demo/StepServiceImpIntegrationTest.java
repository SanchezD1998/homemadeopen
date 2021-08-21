package com.acme.homemade.demo;

import com.acme.homemade.demo.domain.model.Step;
import com.acme.homemade.demo.domain.repository.StepRepository;
import com.acme.homemade.demo.domain.repository.RecipeRepository;
import com.acme.homemade.demo.domain.service.StepService;
import com.acme.homemade.demo.service.StepServiceImpl;
import com.acme.homemade.demo.execption.ResourceNotFoundException;
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
public class StepServiceImpIntegrationTest {
    @MockBean
    private StepRepository stepRepository;

    @MockBean
    private RecipeRepository recipeRepository;

    @Autowired
    private StepService stepService;



    @TestConfiguration
    static class  StepServiceImpTestConfiguration {
        @Bean
        public StepService stepService(){
            return new StepServiceImpl();
        }
    }


    @Test
    @DisplayName("When Get Step By Id With Valid Id Then Returns Step")
    public void whenGetStepByIdWithValidIdThenReturnsStep(){
        //Arrange
        Step step = new Step();
        step.setId(1L);
        step.setText("Licuar frutas");
        when(stepRepository.findById(1L)).thenReturn(Optional.of(step));

        //Act
        Step foundStep = stepService.getStepById(1L);

        //Assert
        assertThat(foundStep.getId()).isEqualTo(1L);
    }


    @Test
    @DisplayName("When GetStepById With Invalid Id Then Returns ResourceNotFoundException")
    public void whenGetStepByIdWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(stepRepository.findById(id)).thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Step", "Id", id);

        //Act
        Throwable exception = catchThrowable(()->{
            Step step = stepService.getStepById(id);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class).hasMessage(expectedMessage);
    }

}

