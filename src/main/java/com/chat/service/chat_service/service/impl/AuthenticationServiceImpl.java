package com.chat.service.chat_service.service.impl;

import com.chat.service.chat_service.exception.UserInputException;
import com.chat.service.chat_service.exception.UserNotFoundException;
import com.chat.service.chat_service.mapper.UserMapper;
import com.chat.service.chat_service.model.dto.*;
import com.chat.service.chat_service.model.entity.User;
import com.chat.service.chat_service.repository.UserRepository;
import com.chat.service.chat_service.security.JwtService;
import com.chat.service.chat_service.service.AuthenticationService;
import com.chat.service.chat_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public void register(RegisterUserDto user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresentOrElse(
                        userByEmail -> {
                            throw UserInputException
                                    .duplicateEmail(userByEmail.getEmail());
                        },
                        () -> userRepository.findByUsername(user.getUsername())
                                .ifPresentOrElse(
                                        userByUsername -> {
                                            throw UserInputException
                                                    .duplicateUsername(userByUsername.getUsername());
                                        },
                                        () -> {
                                            userRepository.save(userMapper.toUser(user));
                                            log.info("register :: successfully registered user with username: {}",
                                                    user.getUsername());
                                        }));
    }

    @Override
    public LoginResponseDto login(LoginUserDto user) {
        log.info("login :: login user with username: {}...", user.getUsername());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        log.info("login :: login user with username: {}, success", user.getUsername());
        User loggedInUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> UserNotFoundException.userWithUsernameNotFound(user.getUsername()));

        return LoginResponseDto.builder()
                .token(jwtService.generateToken(loggedInUser))
                .expiresIn(jwtService.getExpirationTime())
                .build();
    }
}
