package com.acme.homemade.demo.controller;

import com.acme.homemade.demo.domain.model.Chat;
import com.acme.homemade.demo.domain.service.ChatService;
import com.acme.homemade.demo.resource.ChatResource;
import com.acme.homemade.demo.resource.SaveChatResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChatUserController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ChatService chatService;

    @Operation(summary = "Assign Tag to Post", description = "Establishes association between chat and user", tags = {"Chats"})
    @PostMapping("/users/{userId}/chats/{chatId}")
    public ChatResource assignChatUser(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "chatId") Long chatId) {
        return convertToResource(chatService.assignChatUser(chatId, userId));
    }

    @Operation(summary = "Remove assignment between Tag to Post", description = "Ends association between chat and user", tags = {"Chats"})
    @DeleteMapping("/users/{userId}/chats/{chatId}")
    public ChatResource unassignChatUser(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "chatId") Long chatId) {
        return convertToResource(chatService.unassignChatUser(chatId, userId));
    }

    private Chat convertToEntity(SaveChatResource resource) {
        return mapper.map(resource, Chat.class);
    }


    private ChatResource convertToResource(Chat entity) {
        return mapper.map(entity, ChatResource.class);
    }
}
