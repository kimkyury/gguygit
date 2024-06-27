package org.bnksys.chat.controllers;

import org.bnksys.chat.models.ChatMessage;
import org.bnksys.chat.services.ChatService;
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
    private ChatService chatService;

    @MessageMapping("/chat/enter")
    public void enter(@Payload ChatMessage chatMessage) {
        chatMessage.setContent(chatMessage.getSender() + "님이 입장하셨습니다.");
        chatService.sendMessage(chatMessage);
    }

    @MessageMapping("/chat/message")
    public void message(@Payload ChatMessage chatMessage) {
        chatService.sendMessage(chatMessage);
    }

}
