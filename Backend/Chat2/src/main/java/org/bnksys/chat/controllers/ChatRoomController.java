package org.bnksys.chat.controllers;

import org.bnksys.chat.configs.KafkaConsumerConfig;
import org.bnksys.chat.dtos.ChatMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ChatRoomController {

    @Autowired
    private KafkaTemplate<String, ChatMessageDto> kafkaTemplate;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private KafkaConsumerConfig kafkaConsumerConfig;

    private Map<Long, ConcurrentKafkaListenerContainerFactory<String, ChatMessageDto>> userFactories = new HashMap<>();


    /***
     * 채팅방에 입장한다 (/pub/chat/enter)
     * @param message
     */
    @MessageMapping("/chat/enter")
    public void enterRoom(@Payload ChatMessageDto message) {

        message.setType(ChatMessageDto.MessageType.ENTER);

        // chatroom_{chatroomId}_user_{userId} 형태로 groupId를 생성한다
        String groupId = "chatroom_" + message.getChatroomId() + "_user_" + message.getSenderId();
        userFactories.putIfAbsent(
                message.getSenderId(),
                kafkaConsumerConfig.kafkaListenerContainerFactory(groupId)
        );

        kafkaTemplate.send("chatroom_" + message.getChatroomId(), message);
    }

    @MessageMapping("/chat/message")
    public void sendMessage(@Payload ChatMessageDto message) {

        message.setType(ChatMessageDto.MessageType.CHAT);
        kafkaTemplate.send("chatroom_" + message.getChatroomId(), message);
    }

}
