package com.chat.service.chat_service.exception;

import com.chat.service.chat_service.exception.common.InputException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;

@Slf4j
public class UserInputException extends InputException {
    public UserInputException(String message) {
        super(message);
    }

    public static UserInputException duplicateUsername(String username) {
        log.error("duplicateUsername :: user with username: {} already exists!", username);
        return new UserInputException(
                MessageFormatter.format("User with Username: {} already exists!", username).getMessage());
    }

    public static UserInputException duplicateEmail(String email) {
        log.error("duplicateEmail :: user with email: {} already exists!", email);
        return new UserInputException(
                MessageFormatter.format("User with Email: {} already exists!", email).getMessage());
    }
}
