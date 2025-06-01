package org.kgyury.chat.repositories;

import org.kgyury.chat.entities.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {


}
