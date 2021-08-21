package com.acme.homemade.demo.controller;

import com.acme.homemade.demo.domain.model.Chat;
import com.acme.homemade.demo.domain.model.Recipe;
import com.acme.homemade.demo.domain.service.ChatService;
import com.acme.homemade.demo.resource.ChatResource;
import com.acme.homemade.demo.resource.RecipeResource;
import com.acme.homemade.demo.resource.SaveChatResource;
import com.acme.homemade.demo.resource.SaveRecipeResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ChatController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ChatService chatService;

    @Operation(summary = "Create Chat", description = "Create a new Chat", tags = {"Chats"})
    @PostMapping("/users/{userId}/chat")
    public ChatResource createRecipe(
            @PathVariable(value = "userId") Long userId,
            @Valid @RequestBody SaveChatResource resource) {
        return convertToResource(chatService.createChat( convertToEntity(resource)));

    }

    @Operation(summary = "Delete Chat", description = "Delete Chat with given Id", tags = {"Chats"})
    @DeleteMapping("/chat/{chatId}/")
    public ResponseEntity<?> deleteRecipe(@PathVariable(value = "chatId") Long chatId){
        return chatService.deleteChat(chatId);
    }

    private Chat convertToEntity(SaveChatResource resource) {
        return mapper.map(resource, Chat.class);
    }


    private ChatResource convertToResource(Chat entity) {
        return mapper.map(entity, ChatResource.class);
    }
}
