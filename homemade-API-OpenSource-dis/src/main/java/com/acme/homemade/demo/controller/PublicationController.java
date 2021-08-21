package com.acme.homemade.demo.controller;

import com.acme.homemade.demo.domain.model.Publication;
import com.acme.homemade.demo.domain.service.PublicationService;
import com.acme.homemade.demo.resource.PublicationResource;
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
public class PublicationController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PublicationService publicationService;

    @Operation(summary = "Get publications", description = "Get All UserChef by userId", tags = {"publications"})
    @GetMapping("/publications/users/{userId}")
    public Page<PublicationResource> getAllPublicationByUserId(@PathVariable Long userId, Pageable pageable){
        Page<Publication> publicationPage = publicationService.getAllPublicationByUserId(userId, pageable);
        List<PublicationResource> resource= publicationPage
                .getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resource, pageable, resource.size());
    }

    @Operation(summary = "Get publications", description = "Get All UserChef", tags = {"publications"})
    @GetMapping("/publications")
    public Page<PublicationResource> getAllPublications (Pageable pageable){
        Page<Publication> publicationPage = publicationService.getAllPublication(pageable);
        List<PublicationResource> resource= publicationPage
                .getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resource, pageable, resource.size());
    }


    @Operation(summary = "Create publications", description = "Create a new publications", tags = {"publications"})
    @PostMapping("/publications/users/{userId}")
    public PublicationResource createPublication(@PathVariable Long userId, @Valid @RequestBody SavePublicationResource resource){
        return convertToResource(publicationService.createPublication(userId, convertToEntity(resource)));
    }

    @Operation(summary = "Update publications", description = "Update publications for given Id", tags = {"publications"})
    @PutMapping("/publications/{publicationId}/users/{userId}")
    public PublicationResource updatePublication(@PathVariable(value = "publicationId") Long publicationId,
                                                 @PathVariable(value = "userId") Long userId,
                                                 @Valid @RequestBody SavePublicationResource resource){
        return convertToResource(publicationService.updatePublication(userId, publicationId, convertToEntity(resource)));
    }

    @Operation(summary = "Delete publications", description = "Delete publications with given Id", tags = {"publications"})
    @DeleteMapping("/publications/{publicationId}/users/{userId}")
    public ResponseEntity<?> deletePublication(@PathVariable(value = "publicationId") Long publicationId,
                                            @PathVariable(value = "userId") Long userId){
        return publicationService.deletePublication(userId, publicationId);
    }

    private Publication convertToEntity(SavePublicationResource resource) {
        return mapper.map(resource, Publication.class);
    }


    private PublicationResource convertToResource(Publication entity) {
        return mapper.map(entity, PublicationResource.class);
    }


}
