package com.chat.service.chat_service.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorMessageDto {
    private final String message;
    private final Integer status;
    private final String error;
    private final String path;
    private final LocalDateTime timestamp;
}
