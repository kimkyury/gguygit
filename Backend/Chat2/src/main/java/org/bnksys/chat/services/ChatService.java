package org.bnksys.chat.services;

import java.util.List;
import javax.transaction.Transactional;
import org.bnksys.chat.entities.Chatroom;
import org.bnksys.chat.repositories.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ChatService {

    private final String CHATROOM_TOPIC_PREFIX = "chatroom-";
    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private ChatRoomRepository chatroomRepository;

    /***
     * 채팅방을 개설하고 채팅방 Topic을 생성한다
     */
    public void createChatroom(String name) {

        ChatRoom chatroom = ChatRoom.of(name);
        chatroom = chatroomRepository.save(chatroom);

        kafkaProducerService.createChatroom(name, chatroom.getId());
    }

    public List<Chatroom> getChatroomList() {

        return chatroomRepository.findAll();
    }

}
