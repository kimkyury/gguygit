package org.bnksys.chat.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
import org.bnksys.chat.dtos.ChatMessageDto;

@Entity
@Table(name = "chat_messages")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_room_id", nullable = false)
    @Getter
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    @Getter
    private User sender;

    @ManyToOne
    @JoinColumn(name = "target_user_id")
    @Getter
    @Setter
    private User targetUser; // targetUserId 대신 User 엔티티를 참조

    @Column(nullable = false)
    @Setter
    private String content;

    @Column(nullable = false)
    private String type;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private ChatMessage(){

    }

    private ChatMessage (Long id, ChatRoom chatRoom, User sender, User targetUser, String content, String type, LocalDateTime createdAt){
        this.id = id;
        this.chatRoom = chatRoom;
        this.sender = sender;
        this.targetUser = targetUser;
        this.content = content;
        this.type = type;
        this.createdAt = createdAt;
    }

    public static ChatMessage of(ChatMessageDto dto, User sendUser, User targetUser, ChatRoom chatRoom, LocalDateTime createdAt){
        return new ChatMessage(dto.getId(), chatRoom, sendUser, targetUser, dto.getContent(), dto.getType(), createdAt);
    }
}
