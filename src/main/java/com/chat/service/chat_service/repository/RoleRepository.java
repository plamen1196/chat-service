package com.chat.service.chat_service.repository;

import com.chat.service.chat_service.model.entity.Role;
import com.chat.service.chat_service.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}
