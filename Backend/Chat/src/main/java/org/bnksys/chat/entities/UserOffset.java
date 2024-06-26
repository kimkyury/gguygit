package org.bnksys.chat.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_offsets")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserOffset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long chatroomId;

    @Setter
    private Long offset;

    private UserOffset(Long userId, Long chatroomId) {
        this.userId = userId;
        this.chatroomId = chatroomId;
    }

    public static UserOffset of(Long userId, Long chatroomId) {
        return new UserOffset(userId, chatroomId);
    }
}
