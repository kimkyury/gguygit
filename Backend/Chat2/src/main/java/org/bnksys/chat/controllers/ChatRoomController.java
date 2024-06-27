package org.bnksys.chat.controllers;

import java.util.List;
import org.bnksys.chat.models.ChatRoom;
import org.bnksys.chat.repositories.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatRoomController {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @PostMapping
    public ResponseEntity<ChatRoom> createRoom(@RequestParam String chatroomName) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(chatroomName);
        return ResponseEntity.ok(chatRoomRepository.save(chatRoom));
    }

    @GetMapping
    public ResponseEntity<List<ChatRoom>> getAllRooms() {
        return ResponseEntity.ok(chatRoomRepository.findAll());
    }
}
