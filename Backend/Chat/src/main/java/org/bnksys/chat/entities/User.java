package org.bnksys.chat.entities;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private Timestamp createdAt;

    private User(String username) {
        this.username = username;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public static User of(String username) {
        return new User(username);
    }
}
