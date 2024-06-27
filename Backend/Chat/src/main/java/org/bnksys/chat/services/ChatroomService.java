package org.bnksys.chat.services;

import org.bnksys.chat.entities.Chatroom;
import org.bnksys.chat.repositories.ChatroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class ChatroomService {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private ChatroomRepository chatroomRepository;

    private final String CHATROOM_TOPIC_PREFIX = "chatroom-";

    /***
     * 채팅방을 개설하고 채팅방 Topic을 생성한다
     */
    public void createChatroom(String name) {

        Chatroom chatroom = Chatroom.of(name);
        chatroom = chatroomRepository.save(chatroom);

        kafkaProducerService.createChatroom(name, chatroom.getId());

    }

    public List<Chatroom> getChatroomList() {

        return chatroomRepository.findAll();
    }

    public void joinChatroom() {

    }


    private String createRandomChatroomName() {
        Random random = new Random();
        return String.valueOf(1000 + random.nextInt(9000));
    }
}
