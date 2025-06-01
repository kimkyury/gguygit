package org.bnksys.chat.repositories;

import org.bnksys.chat.entities.UserChatRoom;
import org.bnksys.chat.entities.UserChatRoomId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserChatRoomRepository extends JpaRepository<UserChatRoom, UserChatRoomId> {
    List<UserChatRoom> findByUserId(Long userId);
    List<UserChatRoom> findByChatRoomId(Long chatRoomId);
}
