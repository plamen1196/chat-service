package com.chat.service.chat_service.service.impl;

import com.chat.service.chat_service.exception.RoleNotFoundException;
import com.chat.service.chat_service.mapper.RoleMapper;
import com.chat.service.chat_service.model.dto.RoleDto;
import com.chat.service.chat_service.model.enums.RoleEnum;
import com.chat.service.chat_service.repository.RoleRepository;
import com.chat.service.chat_service.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;
    @Override
    public RoleDto getRoleByName(String roleName) {
        log.info("getRoleByName :: service is fetching role with name: {}", roleName);
        return roleRepository.findByName(RoleEnum.valueOf(roleName))
                .map(roleMapper::toRoleDto)
                .orElseThrow(() -> RoleNotFoundException.roleWithNameNotFound(roleName));
    }

    @Override
    public RoleDto getRoleById(Long id) {
        log.info("getRoleById :: service is fetching role with id: {}", id);
        return roleRepository.findById(id)
                .map(roleMapper::toRoleDto)
                .orElseThrow(() -> RoleNotFoundException.roleWithIdNotFound(id));
    }
}
