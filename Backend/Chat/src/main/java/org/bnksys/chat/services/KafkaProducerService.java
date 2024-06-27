package org.bnksys.chat.services;

import org.bnksys.chat.entities.ChatroomMember;
import org.bnksys.chat.repositories.ChatroomMemberRepository;
import org.bnksys.chat.requests.EnterRequest;
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

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    /***
     * 유저가 방에 참여한다
     * 유저가 특정 방에 대한 Topic을 구독한다
     * @param request 입장 요청 정보로, 유저 아이디와 방 아이디를 포함한다
     */
    public void enter(EnterRequest request) {

        ChatroomMember chatroomMember = ChatroomMember.of(request.getChatroomId(), request.getUserId());
        chatroomMemberRepository.save(chatroomMember);

        String topic = "chatroom-" + request.getChatroomId();
        kafkaTemplate.send(topic, request.getUserId().toString() + "가 입장하였습니다");

    }

    public void createChatroom(String chatroomName, Long id) {

        kafkaTemplate.send(CHATROOM_TOPIC_PREFIX + id, "채팅방이 생성되었습니다. ");

    }

    public void sendMessage(Long chatroomId, Long senderId, String Message) {

//        ChatMessage chatMessage


    }
}