package org.bnksys.chat.services;

import org.bnksys.chat.repositories.ChatroomMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ChatroomMemberRepository chatroomMemberRepository;

    private final String CHATROOM_TOPIC_PREFIX = "chatroom-";

    public void createChatroom(String chatroomName, Long id) {

        kafkaTemplate.send(CHATROOM_TOPIC_PREFIX + id, "채팅방이 생성되었습니다. ");
    }

}