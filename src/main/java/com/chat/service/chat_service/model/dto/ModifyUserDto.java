package com.chat.service.chat_service.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyUserDto {
    @Email(message = "Email has wrong format")
    @NotNull(message = "Field cannot be null")
    private String email;
    @Size(min = 3, message = "Password is too short")
    @Size(max = 100, message = "Password is too long")
    @NotNull(message = "Field cannot be null")
    private String password;
    @Size(min = 3, message = "Mobile is too short")
    @Size(max = 50, message = "Mobile is too long")
    @NotNull(message = "Field cannot be null")
    private String mobile;
    @Size(min = 3, message = "First name is too short")
    @Size(max = 50, message = "First name is too long")
    @NotNull(message = "Field cannot be null")
    private String firstName;
    @Size(min = 3, message = "Last name is too short")
    @Size(max = 50, message = "Last name is too long")
    @NotNull(message = "Field cannot be null")
    private String lastName;
}
