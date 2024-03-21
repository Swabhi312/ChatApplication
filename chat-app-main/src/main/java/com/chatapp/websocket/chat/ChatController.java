package com.chatapp.websocket.chat;
import java.util.*;

import com.chatapp.websocket.Repository.ChatApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 *
 * This controller to call fetch,delete chat room history, add user to chatroom, send messages to chatroom
 */
@Controller
public class ChatController {

    @Autowired
    private ChatApplicationRepository chatApplicationRepository;

    @GetMapping("/fetch/chatroom/history")
    @ResponseBody
    public List<ChatMessage> fetchChatroomHistory()
    {
        return chatApplicationRepository.findAll();
    }

    @DeleteMapping("/delete/allmessages")
    public ResponseEntity<String> DeleteChatRoomMessages()
    {
      chatApplicationRepository.deleteAll();
       return ResponseEntity.ok("Chat History deleted successfully!.");
     }

    @DeleteMapping("/delete/usermessages/{username}")
    public ResponseEntity<String> DeleteSpecificUserMessages(@PathVariable String username)
    {
       // chatApplicationRepository.deleteByUsername(username);
        return ResponseEntity.ok("Chat History deleted successfully for given user!.");
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(
            @Payload ChatMessage chatMessage
    ) {
        chatApplicationRepository.save(chatMessage);
        return chatMessage;
    }


    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getUsername());
       //headerAccessor.getSessionAttributes().put("password", chatMessage.getPassword());
        return chatMessage;
    }


}
