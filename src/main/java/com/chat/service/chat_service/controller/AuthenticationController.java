package com.chat.service.chat_service.controller;

import com.chat.service.chat_service.model.dto.LoginResponseDto;
import com.chat.service.chat_service.model.dto.LoginUserDto;
import com.chat.service.chat_service.model.dto.RegisterUserDto;
import com.chat.service.chat_service.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegisterUserDto registerUserDto) {
        log.info("register :: calling endpoint to register user");
        authenticationService.register(registerUserDto);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDto login(@Valid @RequestBody LoginUserDto loginUserDto) {
        log.info("login :: calling endpoint to login user");
        return authenticationService.login(loginUserDto);
    }
}
