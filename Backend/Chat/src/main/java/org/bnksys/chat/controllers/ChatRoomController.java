package org.bnksys.chat.controllers;

import org.bnksys.chat.requests.ChatroomUserRequest;
import org.bnksys.chat.services.ChatroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chatroom")
public class ChatRoomController {

    @Autowired
    private ChatroomService chatroomService;

    @PostMapping("")
    public ResponseEntity<String> createChatroom(@RequestBody String name) {

        chatroomService.createChatroom(name);

        return ResponseEntity.ok("Created Chatroom");
    }

    @PostMapping("/join")
    public ResponseEntity<String> joinChatRoom(@RequestBody ChatroomUserRequest request) {
        // chatroomService.joinChatRoom(request.getChatroomId(), request.getUserId()
        return ResponseEntity.ok("Joined chat room");

    }
}
