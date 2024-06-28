package org.bnksys.chat.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UserChatRoomId implements Serializable {
    @Getter
    private Long userId;

    @Getter
    private Long chatRoomId;

    // Getters and Setters, hashCode, equals
}