package com.example.webchat.controller;

import com.example.webchat.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@Slf4j
public class WebSocketController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/publicChatRoom")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        log.info("-- sendMessage Controller --" +
                "\n Sender: " + chatMessage.getSender() +
                "\n Message: " + chatMessage.getContent() +
                "\n Type: " + chatMessage.getType() +
                "\n Date: " + chatMessage.getDate());
        chatMessage.setDate(LocalDateTime.now());
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/publicChatRoom")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        log.info("-- addUser Controller --" +
                "\nSender: " + chatMessage.getSender() +
                "\nMessage: " + chatMessage.getContent() +
                "\nType: " + chatMessage.getType() +
                "\nDate: " + chatMessage.getDate());
        chatMessage.setDate(LocalDateTime.now());
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
