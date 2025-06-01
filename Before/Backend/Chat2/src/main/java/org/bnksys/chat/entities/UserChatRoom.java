package org.bnksys.chat.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_chat_rooms")
@AllArgsConstructor
@NoArgsConstructor
public class UserChatRoom {
    @EmbeddedId
    private UserChatRoomId id = new UserChatRoomId();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("chatRoomId")
    @JoinColumn(name = "chat_room_id")
    @Getter
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "last_read_message_id")
    @Getter
    private ChatMessage lastReadMessage;

    @Column(name = "is_connected", nullable = false)
    @Setter
    private boolean isConnected = false;

    // Getters and Setters
}
