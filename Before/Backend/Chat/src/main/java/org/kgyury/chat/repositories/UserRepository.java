package org.kgyury.chat.repositories;

import org.kgyury.chat.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}