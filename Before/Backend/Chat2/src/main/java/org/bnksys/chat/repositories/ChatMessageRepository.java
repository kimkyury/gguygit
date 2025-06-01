package org.bnksys.chat.repositories;

import org.bnksys.chat.entities.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatRoomIdAndIdGreaterThan(Long chatRoomId, Long lastReadMessageId);
    Long countByChatRoomIdAndIdGreaterThan(Long chatRoomId, Long lastReadMessageId);

}
