package com.acme.homemade.demo.domain.repository;


import com.acme.homemade.demo.domain.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ChatRepository extends JpaRepository<Chat, Long>{

}
