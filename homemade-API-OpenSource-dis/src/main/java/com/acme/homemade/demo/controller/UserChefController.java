package com.acme.homemade.demo.controller;

import com.acme.homemade.demo.domain.model.UserChef;
import com.acme.homemade.demo.domain.service.UserChefService;
import com.acme.homemade.demo.resource.SaveUserChefResource;
import com.acme.homemade.demo.resource.UserChefResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
public class UserChefController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserChefService userChefService;

    @Operation(summary = "Get Posts", description = "Get All UserChef by Pages", tags = {"UserChefs"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All UserChef returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/userChefs")
    public Page<UserChefResource> getAllUserChef(Pageable pageable) {
        Page<UserChef> userChef = userChefService.getAllUserChef(pageable);
        List<UserChefResource> resource = userChef
                .getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resource, pageable, resource.size());
    }

    @GetMapping("/userChefs/{id}")
    public UserChefResource getById(@PathVariable(name = "id") Long userChefId) {
        return convertToResource(userChefService.getUserChefById(userChefId));
    }

    @Operation(summary = "Create UserChef", description = "Create a new UserChef", tags = {"UserChefs"})
    @PostMapping("/userChefs")
    public UserChefResource createUserChef(@Valid @RequestBody SaveUserChefResource resource) {
        UserChef userChef = convertToEntity(resource);
        return convertToResource(userChefService.createUserChef(userChef));
    }

    @Operation(summary = "Update UserChef", description = "Update UserChef for given Id", tags = {"UserChefs"})
    @PutMapping("/userChefs/{userChefId}")
    public UserChefResource updateUserChef(@PathVariable Long userChefId, @RequestBody SaveUserChefResource resource) {
        UserChef userChef = convertToEntity(resource);
        return convertToResource(userChefService.updateUserChef(userChefId, userChef));
    }

    @Operation(summary = "Delete UserChef", description = "Delete UserChef with given Id", tags = {"UserChefs"})
    @DeleteMapping("/userChefs/{userChefId}")
    public ResponseEntity<?> deleteUserChef (@PathVariable Long userChefId){
        return userChefService.deleteUserChef(userChefId);
    }

    public UserChef convertToEntity(SaveUserChefResource resource) {

        return mapper.map(resource, UserChef.class);
    }

    public UserChefResource convertToResource(UserChef entity) {

        return mapper.map(entity, UserChefResource.class);
    }

}
