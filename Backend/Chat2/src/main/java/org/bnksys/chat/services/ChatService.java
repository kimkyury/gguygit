package org.bnksys.chat.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.transaction.Transactional;
import org.bnksys.chat.models.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/***
 * Kafka와 통신하여 메시지를 보내는 서비스
 */
@Service
@Transactional
public class ChatService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(ChatMessage chatMessage) {
        try {
            String message = new ObjectMapper().writeValueAsString(chatMessage);
            kafkaTemplate.send("chat-messages", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
