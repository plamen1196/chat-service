package com.chat.service.chat_service.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDto {
    @Size(min = 3, message = "Username is too short")
    @Size(max = 50, message = "Username is too long")
    @NotNull(message = "Field cannot be null")
    private String username;
    @Size(min = 3, message = "Password is too short")
    @Size(max = 100, message = "Password is too long")
    @NotNull(message = "Field cannot be null")
    private String password;
}
