package com.itso.occupancy.occupancy.config.errorhandler;


import com.itso.occupancy.occupancy.config.errorhandler.bean.ApiError;
import com.itso.occupancy.occupancy.config.errorhandler.customexception.*;
import com.itso.occupancy.occupancy.config.errorhandler.factory.ResponseEntityBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class GlobalResponse extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex) {

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                500,
                ex.getMessage());

        log.warn(ex.getMessage());
        return ResponseEntityBuilder.build(err);

    }

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<Object> handleElementNotFoundException(ElementNotFoundException ex) {

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                120,
                ex.getMessage());

        log.warn(ex.getMessage());
        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleElementNotFoundException(BadCredentialsException ex) {

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED,
                10,
                ex.getMessage());

        log.warn(ex.getMessage());
        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler(UserDisabledException.class)
    public ResponseEntity<Object> handleElementNotFoundException(UserDisabledException ex) {

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED,
                10,
                ex.getMessage());

        log.warn(ex.getMessage());
        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler(VersionException.class)
    public ResponseEntity<Object> handleElementNotFoundException(VersionException ex) {

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                999,
                ex.getMessage());

        log.warn(ex.getMessage());
        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler(UserNotAuthorizeException.class)
    public ResponseEntity<Object> handleUserNotAuthorizeException(UserNotAuthorizeException ex) {

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.FORBIDDEN,
                403,
                ex.getMessage()

        );
        log.warn(ex.getMessage());
        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler(UserLockedException.class)
    public ResponseEntity<Object> handleUserNotAuthorizeException(UserLockedException ex) {

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.FORBIDDEN,
                200,
                ex.getMessage()

        );
        log.warn(ex.getMessage());
        return ResponseEntityBuilder.build(err);
    }


}