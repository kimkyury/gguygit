package org.bnksys.chat.dtos;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {

    private Long id;
    private Long chatRoomId;
    private Long senderId;
    private Long targetUserId;
    private String content;
    private String type;
    private String createdAt; // TODO: LocalDateTime로 직렬화 시켜야 함
}
