package org.bnksys.chat.services;

import org.bnksys.chat.dtos.ChatMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topicPattern = "chatroom_*", containerFactory = "kafkaListenerContainerFactory")
    public void listen(@Payload ChatMessageDto message) {
        messagingTemplate.convertAndSend("/topic/chatroom_" + message.getChatroomId(), message);
    }
}
