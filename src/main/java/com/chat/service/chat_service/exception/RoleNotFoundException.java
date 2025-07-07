package com.chat.service.chat_service.exception;

import com.chat.service.chat_service.exception.common.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;

@Slf4j
public class RoleNotFoundException extends NotFoundException {
    public RoleNotFoundException(String message) {
        super(message);
    }

    public static RoleNotFoundException roleWithNameNotFound(String name) {
        log.error("roleWithNameNotFound :: role with name: {} does not exist!", name);
        return new RoleNotFoundException(
                MessageFormatter.format("Role with name: {} does not exist!", name).getMessage());
    }

    public static RoleNotFoundException roleWithIdNotFound(Long id) {
        log.error("roleWithIdNotFound :: role with id: {} does not exist!", id);
        return new RoleNotFoundException(
                MessageFormatter.format("Role with id: {} does not exist!", id).getMessage());
    }
}
