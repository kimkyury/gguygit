package org.bnksys.chat.services;


import java.time.LocalDateTime;
import org.bnksys.chat.dtos.ChatMessageDto;
import org.bnksys.chat.entities.ChatMessage;
import org.bnksys.chat.entities.ChatRoom;
import org.bnksys.chat.entities.User;
import org.bnksys.chat.repositories.ChatMessageRepository;
import org.bnksys.chat.repositories.ChatRoomRepository;
import org.bnksys.chat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private UserRepository userRepository;

    /***
     * Dto To Entity 컨버터
     * @param message
     * @return
     */
    public ChatMessage convertDtoToEntity(ChatMessageDto message) {

        User sendUser = userRepository.findById(message.getSenderId()).get();
        User targetUser = userRepository.findById(message.getTargetUserId()).get();
        ChatRoom chatRoom = chatRoomRepository.findById(message.getChatRoomId()).get();
        LocalDateTime createdAt = LocalDateTime.parse(message.getCreatedAt());

        return ChatMessage.of(message, sendUser, targetUser, chatRoom, createdAt);
    }

    public CHatMessage

    public void saveChatMessage(ChatMessage chatMessage){
        chatMessageRepository.save(chatMessage);
    }



}
