package com.chatapp.websocket.config;

import com.chatapp.websocket.chat.ChatMessage;
import com.chatapp.websocket.chat.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
/**
 * This class represents events in websocket
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        String password = (String) headerAccessor.getSessionAttributes().get("password");

        if (username != null) {
            log.info("user disconnected: {}", username);
            var chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVE)
                    .username(username)
                    .password(password)
                    .build();
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }

}
