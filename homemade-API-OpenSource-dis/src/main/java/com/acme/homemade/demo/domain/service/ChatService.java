package com.acme.homemade.demo.domain.service;

import com.acme.homemade.demo.domain.model.Chat;
import com.acme.homemade.demo.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ChatService {

    Chat getChatId (Long chatId);

    Chat createChat ( Chat chat);

    ResponseEntity<?> deleteChat (Long chatId);

    Chat assignChatUser (Long ChatId, Long userId);

    Chat unassignChatUser (Long ChatId, Long userId);

}
