package org.bnksys.chat.services;

import org.bnksys.chat.dtos.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    private SimpMessagingTemplate template;

    @KafkaListener(topics = "chat", groupId = "chat-group")
    public void consume(@Payload ChatMessage message,  @Header("simpSessionId") String sessionId) {

        message.getReadBy().add(sessionId);
        template.convertAndSend("/topic/messages", message);
    }
}