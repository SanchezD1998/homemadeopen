package com.acme.homemade.demo.service;

import com.acme.homemade.demo.domain.model.Step;
import com.acme.homemade.demo.domain.repository.RecipeRepository;
import com.acme.homemade.demo.domain.repository.StepRepository;
import com.acme.homemade.demo.domain.service.StepService;
import com.acme.homemade.demo.execption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StepServiceImpl implements StepService {

    @Autowired
    private StepRepository stepRepository;

    @Autowired
    private RecipeRepository recipeRepository;


    @Override
    public Step getStepById(Long stepId) {
        return stepRepository.findById(stepId).orElseThrow(()->new ResourceNotFoundException("Step","Id",stepId));
    }

    @Override
    public Page<Step> getAllStepByRecipeId(Long recipeId, Pageable pageable) {
        return stepRepository.findByRecipeId(recipeId,pageable);
    }

    @Override
    public Step createStep( Long recipeId, Step step) {
        return recipeRepository.findById(recipeId).map(recipe -> {
            step.setRecipe(recipe);
            return stepRepository.save(step);
        }).orElseThrow(()->new ResourceNotFoundException("Recipe","Id",recipeId));
    }

    @Override
    public Step updateStep(Long stepId, Step step) {
        return stepRepository.findById(stepId).map( step1 ->{
            step1.setText(step.getText());
            step1.setImage(step.getImage());
            return stepRepository.save(step1);
        }).orElseThrow(()->new ResourceNotFoundException("Step","Id",stepId));
    }

    @Override
    public ResponseEntity<?> deleteStep(Long stepId, Long recipeId) {
        if (!recipeRepository.existsById(recipeId))
            throw new ResourceNotFoundException("Recipe", "Id", recipeId);
        return stepRepository.findById(stepId).map( step -> {
            stepRepository.delete(step);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Step", "Id", stepId));
    }
}
