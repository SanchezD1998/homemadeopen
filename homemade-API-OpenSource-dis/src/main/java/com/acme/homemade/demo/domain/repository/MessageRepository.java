package com.acme.homemade.demo.domain.repository;


import com.acme.homemade.demo.domain.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Page<Message> findByUserId (Long userId, Pageable pageable);
    Page<Message> findByChatId (Long chatId, Pageable pageable);

}
