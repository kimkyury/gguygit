package org.bnksys.chat.dtos;

import lombok.Getter;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatMessage {

    private String sender;
    private String content;
    private final LocalDateTime timestamp;
    private final Set<String> readBy;

    public ChatMessage() {
        this.timestamp = LocalDateTime.now();
        this.readBy = new HashSet<>();
    }

    public void setInfo(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    public void addReadBy(String user) {
        this.readBy.add(user);
    }
}
