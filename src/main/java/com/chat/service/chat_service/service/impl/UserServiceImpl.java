package com.chat.service.chat_service.service.impl;

import com.chat.service.chat_service.exception.UserNotFoundException;
import com.chat.service.chat_service.mapper.UserMapper;
import com.chat.service.chat_service.model.dto.ModifyUserDto;
import com.chat.service.chat_service.model.dto.UserDto;
import com.chat.service.chat_service.model.entity.User;
import com.chat.service.chat_service.repository.UserRepository;
import com.chat.service.chat_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto getUserByUsername(String username) {
        log.info("getUserByUsername :: service is fetching user with username: {}", username);
        return userRepository.findByUsername(username)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> UserNotFoundException.userWithUsernameNotFound(username));
    }

    @Override
    public UserDto getUserByEmail(String email) {
        log.info("getUserByEmail :: service is fetching user with email: {}", email);
        return userRepository.findByEmail(email)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> UserNotFoundException.userWithEmailNotFound(email));
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("getAllUsers :: service is fetching all users");
        return userRepository.findAll().stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        log.info("getUserById :: service is fetching user with id: {}", id);
        return userRepository.findById(id)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> UserNotFoundException.userWithIdNotFound(id));
    }

    @Override
    public UserDto modifyUser(Long id, ModifyUserDto user) {
        checkIfUserExistsById(id);
        User modifiedUser = userRepository.save(userMapper.toUser(user, id));
        log.info("modifyUser :: successfully modified user with id: {}", id);
        return userMapper.toUserDto(modifiedUser);
    }

    @Override
    public void deleteUserById(Long id) {
        checkIfUserExistsById(id);
        userRepository.deleteById(id);
        log.info("deleteUserById :: successfully deleted user with id: {}", id);
    }

    private void checkIfUserExistsById(Long id) {
        log.info("checkIfUserExistsById :: service is checking if user with id: {} exists", id);
        userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.userWithIdNotFound(id));
    }
}
