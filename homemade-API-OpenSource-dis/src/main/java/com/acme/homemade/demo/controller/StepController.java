package com.acme.homemade.demo.controller;

import com.acme.homemade.demo.domain.model.Recipe;
import com.acme.homemade.demo.domain.model.Step;
import com.acme.homemade.demo.domain.model.Step;
import com.acme.homemade.demo.domain.service.ChatService;
import com.acme.homemade.demo.domain.service.StepService;
import com.acme.homemade.demo.resource.*;
import com.acme.homemade.demo.resource.StepResource;
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
public class StepController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private StepService stepService;

    @Operation(summary = "Get Recipe By Id", description = "Get Recipes for given Id", tags = {"Steps"})
    @GetMapping("/step/{id}")
    public StepResource getStepById(
            @PathVariable(name = "id") Long stepId) {
        return convertToResource(stepService.getStepById(stepId));
    }

    @Operation(summary = "Get Steps", description = "Get All Steps by RecipesId", tags = {"Steps"})
    @GetMapping("/recipe/{recipeId}/step")
    public Page<StepResource> getAllRecipeByUserChefId(
            @PathVariable Long recipeId, Pageable pageable) {
        Page<Step> stepPage = stepService.getAllStepByRecipeId(recipeId, pageable);
        List<StepResource> resources = stepPage
                .getContent()
                .stream().map(
                        this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Create Step", description = "Create a new Step", tags = {"Steps"})
    @PostMapping("/recipe/{recipeId}/step")
    public StepResource createStep(
            @PathVariable(value = "recipeId") Long recipeId,
            @Valid @RequestBody SaveStepResource resource) {
        return convertToResource(stepService.createStep( recipeId,convertToEntity(resource)));
    }

    @Operation(summary = "Update Step", description = "Update Step for given Id", tags = {"Steps"})
    @PutMapping("/recipes/step/{stepId}")
    public StepResource updateRecipe(@PathVariable(value = "stepId") Long stepId,
                                       @Valid @RequestBody SaveStepResource resource){
        return convertToResource(stepService.updateStep(stepId, convertToEntity(resource)));
    }

    @Operation(summary = "Delete Step", description = "Delete Step with given Id", tags = {"Steps"})
    @DeleteMapping("/recipes/{recipeId}/step/{stepId}")
    public ResponseEntity<?> deleteRecipe(@PathVariable(value = "recipeId") Long recipeId,
                                          @PathVariable(value = "stepId") Long stepId){
        return stepService.deleteStep(stepId, recipeId);
    }

    private Step convertToEntity(SaveStepResource resource) {
        return mapper.map(resource, Step.class);
    }


    private StepResource convertToResource(Step entity) {
        return mapper.map(entity, StepResource.class);
    }
}
