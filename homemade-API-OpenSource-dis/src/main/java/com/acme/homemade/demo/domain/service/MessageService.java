package com.acme.homemade.demo.domain.service;

import com.acme.homemade.demo.domain.model.Message;
import com.acme.homemade.demo.domain.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MessageService {

    Message getMessageById (Long messageId);

    Page<Message> getAllMessageByUserId (Long userId, Pageable pageable);

    Page<Message> getAllMessageByChatId (Long chatId, Pageable pageable);

    Message createMessage (Long userId, Long ChatId ,Message message);

    ResponseEntity<?> deleteMessage (Long userId, Long messageId, Long chatId);

}
