package org.bnksys.chat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    private SimpMessagingTemplate template;

    @KafkaListener(topics = "chat", groupId = "chat-group")
    public void consume(String message) {
        template.convertAndSend("/topic/messages", message);
    }
}