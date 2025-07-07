package com.chat.service.chat_service.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
    private String token;
    private long expiresIn;
}
