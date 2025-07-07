package com.chat.service.chat_service.service;

import com.chat.service.chat_service.model.dto.*;

import java.util.List;

public interface UserService {
    UserDto getUserByUsername(String username);

    UserDto getUserByEmail(String email);

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    void register(RegisterUserDto user);

    LoginResponseDto login(LoginUserDto user);

    UserDto modifyUser(Long id, ModifyUserDto user);

    void deleteUserById(Long id);
}
