package com.acme.homemade.demo.controller;

import com.acme.homemade.demo.domain.model.UserNoChef;
import com.acme.homemade.demo.domain.service.UserNoChefService;
import com.acme.homemade.demo.resource.SaveUserNoChefResource;
import com.acme.homemade.demo.resource.UserNoChefResource;
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
public class UserNoChefController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserNoChefService userNoChefService;

    @Operation(summary = "Get All userNoChefs", description = "Get All available userNoChefs", tags = {"userNoChefs"})
    @GetMapping("/userNoChefs")
    public Page<UserNoChefResource> getAllUserNoChef(Pageable pageable) {
        Page<UserNoChef> userNoChef = userNoChefService.getAllUserNoChef(pageable);
        List<UserNoChefResource> resources = userNoChef.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get userNoChefs By Id", description = "Get userNoChefs for given Id", tags = {"userNoChefs"})
    @GetMapping("/userNoChefs/{id}")
    public UserNoChefResource getUserNoChefById(@PathVariable(name = "id") Long id){
        return convertToResource(userNoChefService.getUserNoChefById(id));
    }

    @Operation(summary = "Create userNoChefs", description = "Create a new userNoChefs", tags = {"userNoChefs"})
    @PostMapping("/userNoChefs")
    public UserNoChefResource createUserNoChef(@Valid @RequestBody SaveUserNoChefResource resource){
        UserNoChef userNoChef = convertToEntity(resource);
        return convertToResource(userNoChefService.createUserNoChef(userNoChef));
    }

    @Operation(summary = "Update userNoChefs", description = "Update userNoChefs with given Id", tags = {"userNoChefs"})
    @PutMapping("/userNoChefs/{id}")
    public UserNoChefResource updateUserNoChefResource(@PathVariable(name = "id") Long userNoChefId,
                                                       @Valid @RequestBody SaveUserNoChefResource resource){
        return convertToResource(userNoChefService.updateUserNoChef(userNoChefId,convertToEntity(resource)));
    }

    @Operation(summary = "Delete UserNoChef", description = "Delete UserNoChef with given Id", tags = {"userNoChefs"})
    @DeleteMapping("/userNoChefs/{id}")
    public ResponseEntity<?> deleteUserChef (@PathVariable(name = "id")  Long id){
        return userNoChefService.deleteUserNoChef(id);
    }

    private UserNoChef convertToEntity(SaveUserNoChefResource resource) {
        return mapper.map(resource, UserNoChef.class);
    }

    private UserNoChefResource convertToResource(UserNoChef entity) {
        return mapper.map(entity, UserNoChefResource.class);
    }
}
