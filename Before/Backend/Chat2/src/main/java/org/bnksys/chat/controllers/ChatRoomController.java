package org.bnksys.chat.controllers;

import java.util.HashMap;
import java.util.List;

import java.util.Map;
import org.bnksys.chat.entities.ChatRoom;
import org.bnksys.chat.entities.UserChatRoom;
import org.bnksys.chat.repositories.ChatMessageRepository;
import org.bnksys.chat.repositories.ChatRoomRepository;
import org.bnksys.chat.repositories.UserChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("api/v1/rooms")
@Controller
public class ChatRoomController {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private UserChatRoomRepository userChatRoomRepository;

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

    @GetMapping("/chat/unreadCounts")
    public Map<Long, Long> getUnreadCounts(@RequestParam Long userId) {
        List<UserChatRoom> userChatRooms = userChatRoomRepository.findByUserId(userId);
        Map<Long, Long> unreadCounts = new HashMap<>();
        for (UserChatRoom userChatRoom : userChatRooms) {
            Long chatRoomId = userChatRoom.getChatRoom().getId();
            Long lastReadMessageId = userChatRoom.getLastReadMessage() != null ? userChatRoom.getLastReadMessage().getId() : 0L;

            Long unreadCount =  chatMessageRepository.countByChatRoomIdAndIdGreaterThan(chatRoomId, lastReadMessageId);
            unreadCounts.put(chatRoomId, unreadCount);
        }
        return unreadCounts;
    }
}
