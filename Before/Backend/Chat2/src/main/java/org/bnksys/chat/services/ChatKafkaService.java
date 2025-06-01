package org.bnksys.chat.services;

import javax.transaction.Transactional;

import org.bnksys.chat.dtos.ChatMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/***
 * Kafka와 통신하여 메시지를 보내는 서비스
 */
@Service
@Transactional
public class ChatKafkaService {

    @Autowired
    private KafkaTemplate<String, ChatMessageDto> kafkaTemplate;


    /***
     * Config에서 ChatMessage에 대한 직렬화를 설정했기에 바로 객체로 받을 수 있다
     * 기본적으로 Topic의 이름은 "chat-messages"로 설정
     * @param message
     */
    public void sendMessage(ChatMessageDto message) {

        kafkaTemplate.send("chat-messages", message);
    }
}
