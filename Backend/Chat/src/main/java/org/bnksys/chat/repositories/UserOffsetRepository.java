package org.bnksys.chat.repositories;

import org.bnksys.chat.entities.User;
import org.bnksys.chat.entities.UserOffset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserOffsetRepository extends JpaRepository<UserOffset, Long> {

    Optional<UserOffset> findByUserIdAndChatroomId(Long userId, Long chatroomId);

}