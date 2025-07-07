package com.chat.service.chat_service.model.dto;

import com.chat.service.chat_service.model.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegisterUserDto {
    @JsonIgnore
    private List<String> roles = List.of(RoleEnum.ROLE_USER.name());
    @Size(min = 3, message = "Username is too short")
    @Size(max = 50, message = "Username is too long")
    @NotNull(message = "Field cannot be null")
    private String username;
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
