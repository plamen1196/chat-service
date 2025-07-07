package com.chat.service.chat_service.exception;

import com.chat.service.chat_service.exception.common.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;

@Slf4j
public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public static UserNotFoundException userWithUsernameNotFound(String username) {
        log.error("userWithUsernameNotFound :: user with username: {} does not exist!", username);
        return new UserNotFoundException(
                MessageFormatter.format("User with username: {} does not exist!", username).getMessage());
    }

    public static UserNotFoundException userWithEmailNotFound(String email) {
        log.error("userWithEmailNotFound :: user with email: {} does not exist!", email);
        return new UserNotFoundException(
                MessageFormatter.format("User with email: {} does not exist!", email).getMessage());
    }

    public static UserNotFoundException userWithIdNotFound(Long id) {
        log.error("userWithIdNotFound :: user with id: {} does not exist!", id);
        return new UserNotFoundException(
                MessageFormatter.format("User with id: {} does not exist!", id).getMessage());
    }
}
