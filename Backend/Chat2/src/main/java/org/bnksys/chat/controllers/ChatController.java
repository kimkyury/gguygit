package org.bnksys.chat.controllers;

import org.bnksys.chat.entities.*;
import org.bnksys.chat.repositories.UserChatRoomRepository;
import org.bnksys.chat.repositories.UserRepository;
import org.bnksys.chat.services.ChatKafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

/***
 * Stomp를 이용한 채팅 컨트롤러
 */
@RestController
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserChatRoomRepository userChatRoomRepository;

    @Autowired
    private ChatKafkaService chatKafkaService;

    @MessageMapping("/enter")
    public void enter(@Payload ChatMessage chatMessage) {



        chatMessage.setContent(chatMessage.getSender() + "님이 입장하셨습니다.");
        chatKafkaService.sendMessage(chatMessage);
    }

    /***
     * TODO: 해당 사용자의 연결이 끊기면 DB에는 데이터가 off인가 Delete인가?
     * @param chatMessage
     */
    @MessageMapping("/leave")
    public void leave(@Payload ChatMessage chatMessage) {
        UserChatRoom userChatRoom = userChatRoomRepository.findById(
                                                              new UserChatRoomId(chatMessage.getSender().getId(), chatMessage.getChatRoom().getId()))
                                                          .orElseThrow(() -> new RuntimeException(
                                                              "UserChatRoom not found"));

        userChatRoomRepository.delete(userChatRoom);
//        chatMessage.setContent(chatMessage.getSender().getUsername() + "님이 퇴장하셨습니다.");
        chatKafkaService.sendMessage(chatMessage);
    }

    @MessageMapping("/message")
    public void message(@Payload ChatMessage chatMessage) {

        chatKafkaService.sendMessage(chatMessage);

    }

    @MessageMapping("/invite")
    public void invite(@Payload ChatMessage chatMessage) {

        User targetUser = userRepository.findById(chatMessage.getTargetUser().getId())
                                        .orElseThrow(() -> new RuntimeException("User not found"));

        ChatRoom chatRoom = chatMessage.getChatRoom();
        UserChatRoom userChatRoom = new UserChatRoom(
            new UserChatRoomId(targetUser.getId(), chatRoom.getId()), targetUser, chatRoom, null,
            false);
        userChatRoomRepository.save(userChatRoom);

        chatMessage.setContent(
            chatMessage.getSender().getUsername() + "님이 " + targetUser.getUsername()
                + "님을 초대하였습니다.");
        chatKafkaService.sendMessage(chatMessage);

        simpMessagingTemplate.convertAndSend("/queue/invite/" + targetUser.getId(), chatMessage);
    }

    @MessageMapping("/kick")
    public void kick(@Payload ChatMessage chatMessage) {

        User targetUser = userRepository.findById(chatMessage.getTargetUser().getId())
                                        .orElseThrow(() -> new RuntimeException("User not found"));

        UserChatRoom userChatRoom = userChatRoomRepository.findById(
            new UserChatRoomId(targetUser.getId(), chatMessage.getChatRoom().getId())).orElseThrow(
            () -> new RuntimeException("UserChatRoom not found"));

        userChatRoomRepository.delete(userChatRoom);

        chatMessage.setContent(
            chatMessage.getSender().getUsername() + "님이 " + targetUser.getUsername()
                + "님을 강퇴하였습니다.");

        chatKafkaService.sendMessage(chatMessage);
        simpMessagingTemplate.convertAndSend("/queue/kick/" + targetUser.getId(), chatMessage);
    }
}
