package com.chat.service.chat_service.service;


import com.chat.service.chat_service.model.dto.RoleDto;

public interface RoleService {
    RoleDto getRoleByName(String roleName);

    RoleDto getRoleById(Long id);
}
