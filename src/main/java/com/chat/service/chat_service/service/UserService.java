package com.chat.service.chat_service.service;

import com.chat.service.chat_service.model.dto.ModifyUserDto;
import com.chat.service.chat_service.model.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserByUsername(String username);

    UserDto getUserByEmail(String email);

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto modifyUser(Long id, ModifyUserDto user);

    void deleteUserById(Long id);
}
