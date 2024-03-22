package com.chatapp.websocket.service;

import com.chatapp.websocket.chat.ChatMessage;

import java.util.List;

public interface ChatappService {
     List<ChatMessage> ChatappMessages();
     void DeleteAllMessages();
     ChatMessage saveChatMessages(ChatMessage chatMessages);
}
