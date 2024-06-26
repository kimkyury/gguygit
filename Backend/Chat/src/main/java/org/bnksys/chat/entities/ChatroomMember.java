package org.bnksys.chat.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "chatroom_members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatroomMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long chatroomId;

    private Long userId;

    private Timestamp joinedAt;

    public ChatroomMember(Long chatroomId, Long userId) {
        this.chatroomId = chatroomId;
        this.userId = userId;
        this.joinedAt = new Timestamp(System.currentTimeMillis());
    }
}
