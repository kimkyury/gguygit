package org.bnksys.chat.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatMessageDto {

    private Long chatroomId;
    private Long senderId;
    private String message;
    private final LocalDateTime timestamp;
    private final Set<String> readBy; // TODO: 읽은 사람 정보, 안 읽은 개수 셀 때 이용

    @Setter
    private MessageType type;

    public enum MessageType {
        CHAT, ENTER, LEAVE
    }

    private ChatMessageDto(Long chatroomId, Long senderId, String message) {
        this.chatroomId = chatroomId;
        this.senderId = senderId;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.readBy = new HashSet<>();
    }

    public static ChatMessageDto of(Long roomId, Long senderId, String message) {
        return new ChatMessageDto(roomId, senderId, message);
    }


    public void addReadBy(String user) {
        this.readBy.add(user);
    }

    public Long countRead() {
        return (long) (readBy.size() - 1);
    }
}
