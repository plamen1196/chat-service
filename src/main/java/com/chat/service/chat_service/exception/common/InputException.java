package com.chat.service.chat_service.exception.common;

public abstract class InputException extends RuntimeException {
    public InputException(String message) {
        super(message);
    }
}
