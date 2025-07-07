package com.chat.service.chat_service.mapper;

import com.chat.service.chat_service.config.MapperConfig;
import com.chat.service.chat_service.model.dto.RoleDto;
import com.chat.service.chat_service.model.entity.Role;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface RoleMapper {
    Role toRole(RoleDto roleDto);
    RoleDto toRoleDto(Role role);
}
