package org.bnksys.chat.repositories;

import org.bnksys.chat.entities.UserChatRoomEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChatRoomEventRepository extends JpaRepository<UserChatRoomEvent, Long> {
}
