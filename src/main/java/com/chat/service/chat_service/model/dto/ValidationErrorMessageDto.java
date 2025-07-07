package com.chat.service.chat_service.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ValidationErrorMessageDto {
    private final String message;
    private final Integer status;
    private final List<ViolationDto> violations;
    private final String path;
    private final LocalDateTime timestamp;
}
