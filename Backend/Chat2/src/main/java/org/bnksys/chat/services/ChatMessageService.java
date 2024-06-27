package org.bnksys.chat.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bnksys.chat.models.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/***
 * Stomp를 이용하여 해당 구독 유저에게 메시지를 보내는 서비스
 * Kafka로부터 메시지를 수신받아, 해당 STOMP TOPIC 구독자에게 메시지 전송
 *
 */
@Service
public class ChatMessageService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @KafkaListener(topics = "chat-messages", groupId = "chat_group_id")
    public void listen(String message) {
        try {
            ChatMessage chatMessage = new ObjectMapper().readValue(message, ChatMessage.class);
            simpMessagingTemplate.convertAndSend("/topic/" + chatMessage.getChatRoomId(),
                chatMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
