package org.bnksys.chat.repositories;

import org.bnksys.chat.entities.ChatroomMember;
import org.bnksys.chat.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatroomMemberRepository extends JpaRepository<ChatroomMember, Long> {

}