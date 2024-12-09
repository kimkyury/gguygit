package org.bnksys.chat.repositories;

import org.bnksys.chat.entities.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {


}
