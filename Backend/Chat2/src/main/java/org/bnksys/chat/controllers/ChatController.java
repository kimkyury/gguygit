package org.bnksys.chat.controllers;

import java.util.List;
import org.bnksys.chat.models.ChatRoom;
import org.bnksys.chat.requests.CreateChatRoomRequest;
import org.bnksys.chat.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rooms")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("")
    public ResponseEntity<String> createChatroom(@RequestBody CreateChatRoomRequest request) {

        chatService.createChatroom(request.getChatroomName());

        return ResponseEntity.ok("Created Chatroom");
    }

    @GetMapping("")
    public ResponseEntity<List<ChatRoom>> getChatroomList() {

        List<ChatRoom> chatroomList = chatService.getChatroomList();

        return ResponseEntity.ok(chatroomList);
    }
//
//
//    @PostMapping("/join")
//    public ResponseEntity<String> joinChatRoom(@RequestBody ChatroomUserRequest request) {
//        // chatroomService.joinChatRoom(request.getChatroomId(), request.getUserId()
//        return ResponseEntity.ok("Joined chat room");
//
//    }
}
