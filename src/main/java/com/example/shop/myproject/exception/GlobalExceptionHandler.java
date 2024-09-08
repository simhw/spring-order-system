package com.example.shop.myproject.exception;


import com.example.shop.myproject.common.ApiResponse;
import com.example.shop.myproject.member.exception.InvalidPasswordException;
import com.example.shop.myproject.member.exception.NoMemberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ApiResponse<String> handleAuthenticationException(AuthenticationException ex) {
        log.error(ex.getMessage(), ex);
        return new ApiResponse<>(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(NoMemberException.class)
    public ApiResponse<String> handleNoMemberException(NoMemberException ex) {
        return new ApiResponse<>(NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> handleInvalidPasswordException(InvalidPasswordException ex) {
        return new ResponseEntity<>(ex.getMessage(), UNAUTHORIZED);
    }
}
