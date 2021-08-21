package com.acme.homemade.demo.service;

import com.acme.homemade.demo.domain.model.Chat;
import com.acme.homemade.demo.domain.model.Message;
import com.acme.homemade.demo.domain.model.User;
import com.acme.homemade.demo.domain.repository.ChatRepository;
import com.acme.homemade.demo.domain.repository.MessageRepository;
import com.acme.homemade.demo.domain.repository.UserRepository;
import com.acme.homemade.demo.domain.service.MessageService;
import com.acme.homemade.demo.execption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;


    @Override
    public Message getMessageById(Long messageId) {
        return messageRepository.findById(messageId).orElseThrow(()->new ResourceNotFoundException("Message","Id",messageId));
    }

    @Override
    public Page<Message> getAllMessageByUserId(Long userId, Pageable pageable) {
        return messageRepository.findByUserId(userId,pageable);
    }

    @Override
    public Page<Message> getAllMessageByChatId(Long chatId, Pageable pageable) {
        return messageRepository.findByUserId(chatId,pageable);
    }

    @Override
    public Message createMessage(Long userId, Long chatId ,Message message) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        Chat chat = chatRepository.findById(chatId).orElseThrow(()->new ResourceNotFoundException("Chat","Id",userId));
        message.setUser(user);
        message.setChat(chat);
        return messageRepository.save(message);
    }


    @Override
    public ResponseEntity<?> deleteMessage(Long userId, Long messageId, Long chatId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        Chat chat = chatRepository.findById(chatId).orElseThrow(()->new ResourceNotFoundException("Chat","Id",userId));
        Message message = messageRepository.findById(messageId).orElseThrow(()->new ResourceNotFoundException("Message","Id",messageId));
        messageRepository.delete(message);
        return ResponseEntity.ok().build();
    }
}
