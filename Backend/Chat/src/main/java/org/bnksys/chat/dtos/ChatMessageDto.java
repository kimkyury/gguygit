package org.bnksys.chat.dtos;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatMessage {

    private Long roomId;
    private Long senderId;
    private String message;
    private final LocalDateTime timestamp;
    private final Set<String> readBy; // TODO: 읽은 사람 정보, 안 읽은 개수 셀 때 이용

    private ChatMessage(Long roomId, Long senderId, String message) {
        this.roomId = roomId;
        this.senderId = senderId;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.readBy = new HashSet<>();
    }

    public static ChatMessage of(Long roomId, Long senderId, String message) {
        return new ChatMessage(roomId, senderId, message);
    }

    public void addReadBy(String user) {
        this.readBy.add(user);
    }

    public Long countRead() {
        return (long) (readBy.size() - 1);
    }
}
