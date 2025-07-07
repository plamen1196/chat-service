package com.chat.service.chat_service.handler;

import com.chat.service.chat_service.exception.common.InputException;
import com.chat.service.chat_service.exception.common.NotFoundException;
import com.chat.service.chat_service.model.dto.ErrorMessageDto;
import com.chat.service.chat_service.model.dto.ValidationErrorMessageDto;
import com.chat.service.chat_service.model.dto.ViolationDto;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    protected ErrorMessageDto handleNotFoundException(
            NotFoundException ex, ServletWebRequest request) {
        log.error("handleNotFoundException :: exception with message: '{}' was thrown!", ex.getMessage());
        return ErrorMessageDto.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .path(request.getRequest().getRequestURI())
                .build();
    }

    @ExceptionHandler(value = InputException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    protected ErrorMessageDto handleInputException(
            InputException ex, ServletWebRequest request) {
        log.error("handleInputException :: exception with message: '{}' was thrown!", ex.getMessage());
        return ErrorMessageDto.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .path(request.getRequest().getRequestURI())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorMessageDto handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, ServletWebRequest request) {
        log.error("handleMethodArgumentNotValidException :: exception with message: '{}' was thrown!", ex.getMessage());
        ValidationErrorMessageDto validationError = ValidationErrorMessageDto.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequest().getRequestURI())
                .timestamp(LocalDateTime.now())
                .violations(new ArrayList<>())
                .build();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            validationError.getViolations().add(
                    ViolationDto.builder()
                            .fieldName(fieldError.getField())
                            .message(fieldError.getDefaultMessage())
                            .build());
        }
        return validationError;
    }

    @ExceptionHandler(value = {
            AuthenticationException.class,
            JwtException.class
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    protected ErrorMessageDto handleAuthenticationException(
            RuntimeException ex, ServletWebRequest request) {
        log.error("handleAuthenticationException :: exception with message: '{}' and type: '{}' was thrown!",
                ex.getMessage(),
                ex.getClass().getSimpleName());
        return ErrorMessageDto.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .message(ex.getMessage())
                .error(HttpStatus.FORBIDDEN.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .path(request.getRequest().getRequestURI())
                .build();
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ErrorMessageDto handleRuntimeException(
            RuntimeException ex, ServletWebRequest request) {
        log.error("handleRuntimeException :: exception with message: '{}' was thrown!", ex.getMessage());
        return ErrorMessageDto.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .path(request.getRequest().getRequestURI())
                .build();
    }
}
