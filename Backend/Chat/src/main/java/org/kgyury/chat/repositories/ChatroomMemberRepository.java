package org.kgyury.chat.repositories;

import org.kgyury.chat.entities.ChatroomMember;
import org.kgyury.chat.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatroomMemberRepository extends JpaRepository<ChatroomMember, Long> {

}