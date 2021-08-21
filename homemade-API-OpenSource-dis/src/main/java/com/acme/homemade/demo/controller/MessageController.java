package com.acme.homemade.demo.controller;


import com.acme.homemade.demo.domain.model.Message;
import com.acme.homemade.demo.domain.service.MessageService;
import com.acme.homemade.demo.resource.MessageResource;
import com.acme.homemade.demo.resource.SaveMessageResource;
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
public class MessageController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MessageService messageService;


    @Operation(summary = "Get message By Id", description = "Get messages for given Id", tags = {"Messages"})
    @GetMapping("/message/{id}")
    public MessageResource geMessageById(
            @PathVariable(name = "id") Long messagesId) {
        return convertToResource(messageService.getMessageById(messagesId));
    }

    @Operation(summary = "Get Messages by userId", description = "Get All Recipes by userId", tags = {"Messages"})
    @GetMapping("/message/user/{userId}")
    public Page<MessageResource> getAllMessageByUserId(
            @PathVariable Long userId, Pageable pageable) {
        Page<Message> messagePage = messageService.getAllMessageByUserId(userId, pageable);
        List<MessageResource> resources = messagePage
                .getContent()
                .stream().map(
                        this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Messages by chatId", description = "Get All Recipes by chatId", tags = {"Messages"})
    @GetMapping("/message/chat/{chatId}")
    public Page<MessageResource> getAllMessageByChatId(
            @PathVariable Long chatId, Pageable pageable) {
        Page<Message> messagePage = messageService.getAllMessageByChatId(chatId, pageable);
        List<MessageResource> resources = messagePage
                .getContent()
                .stream().map(
                        this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }


    @Operation(summary = "Create Message", description = "Create a new Message", tags = {"Messages"})
    @PostMapping("/users/{userId}/chat/{chatId}/message")
    public MessageResource createRecipe(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "chatId") Long chatId,
            @Valid @RequestBody SaveMessageResource resource) {
        return convertToResource(messageService.createMessage(userId, chatId, convertToEntity(resource)));
    }


    @Operation(summary = "Delete Message", description = "Delete message with given Id", tags = {"Messages"})
    @DeleteMapping("/message/{messageId}/users/{userId}/chat/{chatId}")
    public ResponseEntity<?> deleteRecipe(@PathVariable(value = "messageId") Long messageId,
                                          @PathVariable(value = "userId") Long userId,
                                          @PathVariable(value = "chatId") Long chatId){
        return messageService.deleteMessage(userId, messageId, chatId);
    }

    private Message convertToEntity(SaveMessageResource resource) {
        return mapper.map(resource, Message.class);
    }


    private MessageResource convertToResource(Message entity) {
        return mapper.map(entity, MessageResource.class);
    }

}
