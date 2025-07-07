package com.chat.service.chat_service.controller;

import com.chat.service.chat_service.model.dto.*;
import com.chat.service.chat_service.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        log.info("getAllUsers :: calling endpoint to get all users");
        return userService.getAllUsers();
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        log.info("getUserById :: calling endpoint to get user by id: {}", id);
        return userService.getUserById(id);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(path = "/users", params = "username")
    public UserDto getUserByUsername(@RequestParam(value = "username", required = false) String username) {
        log.info("getUserByUsername :: calling endpoint to get user by username: {}", username);
        return userService.getUserByUsername(username);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(path = "/users", params = "email")
    public UserDto getUserByEmail(@RequestParam(value = "email", required = false) String email) {
        log.info("getUserByEmail :: calling endpoint to get user by email: {}", email);
        return userService.getUserByEmail(email);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegisterUserDto registerUserDto) {
        log.info("register :: calling endpoint to register user");
        userService.register(registerUserDto);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDto login(@Valid @RequestBody LoginUserDto loginUserDto) {
        log.info("login :: calling endpoint to login user");
        return userService.login(loginUserDto);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping("/users/{id}")
    public UserDto modifyUser(@PathVariable("id") Long id,
                              @Valid @RequestBody ModifyUserDto modifyUserDto) {
        log.info("modifyUser :: calling endpoint to modify user with id: {}", id);
        return userService.modifyUser(id, modifyUserDto);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable("id") Long id) {
        log.info("deleteUserById :: calling endpoint to delete user with id: {}", id);
        userService.deleteUserById(id);
    }
}
