package com.chat.service.chat_service.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ViolationDto {
    private final String fieldName;
    private final String message;
}
