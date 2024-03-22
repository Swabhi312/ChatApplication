package com.chatapp.websocket.Repository;

import com.chatapp.websocket.chat.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This class represents chat application repository
 */
@Repository
public interface ChatApplicationRepository extends JpaRepository<ChatMessage, Long> {
  /*  @Query(value = "delete FROM chat_message where username = :username ", nativeQuery = true)
    public void  deleteByUsername(@Param("username") String username);*/
}
