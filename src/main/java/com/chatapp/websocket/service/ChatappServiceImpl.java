package com.chatapp.websocket.service;

import com.chatapp.websocket.Repository.ChatApplicationRepository;
import com.chatapp.websocket.chat.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ChatappServiceImpl implements ChatappService {
    @Autowired
    private ChatApplicationRepository chatApplicationRepository;

    @Override
    public List<ChatMessage> ChatappMessages() {
        return chatApplicationRepository.findAll();
    }

    @Override
    public void DeleteAllMessages() {
         chatApplicationRepository.deleteAll();
    }

    @Override
    public ChatMessage saveChatMessages(ChatMessage chatMessages) {
       return chatApplicationRepository.save(chatMessages);
    }


}
