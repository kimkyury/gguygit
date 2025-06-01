package org.bnksys.chat.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "chat_rooms")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    @OneToMany(mappedBy = "chatRoom")
    private Set<ChatMessage> messages = new HashSet<>();

    @OneToMany(mappedBy = "chatRoom")
    private Set<UserChatRoom> userChatRooms = new HashSet<>();

}


