package com.chat.service.chat_service.service;

import com.chat.service.chat_service.model.dto.*;

import java.util.List;

public interface AuthenticationService {

    void register(RegisterUserDto user);

    LoginResponseDto login(LoginUserDto user);
}
