package com.chat.service.chat_service.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Long id;
    private List<RoleDto> roles;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private String mobile;
    private String firstName;
    private String lastName;
    private Timestamp lastLogon;

}
