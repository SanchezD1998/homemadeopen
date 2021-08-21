package com.acme.homemade.demo.service;

import com.acme.homemade.demo.domain.model.Chat;
import com.acme.homemade.demo.domain.model.User;
import com.acme.homemade.demo.domain.repository.ChatRepository;
import com.acme.homemade.demo.domain.repository.UserRepository;
import com.acme.homemade.demo.domain.service.ChatService;
import com.acme.homemade.demo.execption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat getChatId(Long chatId) {
        return chatRepository.findById(chatId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Chat", "Id", chatId));
    }

    @Override
    public Chat createChat( Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public ResponseEntity<?> deleteChat(Long chatId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new ResourceNotFoundException("Chat", "Id", chatId));
        chatRepository.delete(chat);
        return ResponseEntity.ok().build();
    }

    @Override
    public Chat assignChatUser(Long chatId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User", "Id", userId));
        return chatRepository.findById(chatId).map(
                chat -> chatRepository.save(chat.tagWith(user)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Chat", "Id", chatId));
    }

    @Override
    public Chat unassignChatUser(Long chatId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User", "Id", userId));
        return chatRepository.findById(chatId).map(
                chat -> chatRepository.save(chat.unTagWith(user)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Chat", "Id", chatId));
    }
}
