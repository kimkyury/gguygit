package org.bnksys.chat.entities;

import org.bnksys.chat.entities.UserChatRoom;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(unique = true, nullable = false)
    @Getter
    @Setter
    private String username;

    @Column(nullable = false)
    @Getter
    @Setter
    private String password;

    @OneToMany(mappedBy = "sender")
    private Set<ChatMessage> sentMessages = new HashSet<>();

    @OneToMany(mappedBy = "targetUser")
    private Set<ChatMessage> receivedMessages = new HashSet<>();

}
