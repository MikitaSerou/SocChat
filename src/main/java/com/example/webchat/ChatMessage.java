package com.example.webchat;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;
    private LocalDateTime date;

    public enum MessageType {
        CHAT, JOIN, LEAVE
    }

}
