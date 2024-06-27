package org.bnksys.chat.repositories;

import org.bnksys.chat.models.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {


}
