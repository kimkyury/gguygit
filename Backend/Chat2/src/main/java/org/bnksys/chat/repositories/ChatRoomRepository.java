package org.bnksys.chat.repositories;

import org.bnksys.chat.entities.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<Chatroom, Long> {


}
