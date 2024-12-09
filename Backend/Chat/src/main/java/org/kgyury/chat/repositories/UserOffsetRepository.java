package org.kgyury.chat.repositories;

import org.kgyury.chat.entities.User;
import org.kgyury.chat.entities.UserOffset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserOffsetRepository extends JpaRepository<UserOffset, Long> {

    Optional<UserOffset> findByUserIdAndChatroomId(Long userId, Long chatroomId);

}