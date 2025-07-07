package com.chat.service.chat_service.mapper;


import com.chat.service.chat_service.config.MapperConfig;
import com.chat.service.chat_service.model.dto.ModifyUserDto;
import com.chat.service.chat_service.model.dto.RegisterUserDto;
import com.chat.service.chat_service.model.dto.RoleDto;
import com.chat.service.chat_service.model.dto.UserDto;
import com.chat.service.chat_service.model.entity.Role;
import com.chat.service.chat_service.model.entity.User;
import com.chat.service.chat_service.service.RoleService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Mapper(config = MapperConfig.class,
        uses = {RoleMapper.class})
public abstract class UserMapper {

    @Autowired
    protected RoleService roleService;
    @Autowired
    protected RoleMapper roleMapper;
    @Autowired
    protected PasswordEncoder passwordEncoder;
    protected Function<RegisterUserDto, List<Role>> convertToRoles = userDto -> userDto
            .getRoles().stream()
            .map(role -> roleMapper.toRole(roleService.getRoleByName(role)))
            .collect(Collectors.toList());

    protected Function<User, List<RoleDto>> convertToRolesDto = user -> user
            .getRoles().stream()
            .map(role -> roleMapper.toRoleDto(role))
            .collect(Collectors.toList());

    public abstract User toUser(UserDto userDto);

    @Mapping(target = "roles",
            expression = "java(convertToRoles.apply(userDto))")
    @Mapping(target = "password",
            expression = "java(passwordEncoder.encode(userDto.getPassword()))")
    public abstract User toUser(RegisterUserDto userDto);

    @Mapping(target = "password",
            expression = "java(passwordEncoder.encode(modifyUserDto.getPassword()))")
    public abstract User toUser(ModifyUserDto modifyUserDto, Long id);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles",
            expression = "java(convertToRolesDto.apply(user))")
    public abstract UserDto toUserDto(User user);
}
