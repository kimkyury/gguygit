package org.bnksys.chat.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    private String type;
    private String content;
    private String sender;
    private Long chatRoomId;
}