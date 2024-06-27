package org.bnksys.chat.controllers;

import org.bnksys.chat.services.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private KafkaProducerService kafkaProducer;



    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/messages")
    public void sendMessage(@Payload String message) {
        kafkaProducer.sendMessage("chat", message);
    }
}
