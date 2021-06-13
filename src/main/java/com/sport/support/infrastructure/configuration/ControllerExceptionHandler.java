package com.sport.support.infrastructure.configuration;

import com.sport.support.infrastructure.exception.RecordDoesNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

// TODO: 5/16/2021 error messages will be more precise in terms of status codes

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    @ExceptionHandler(RecordDoesNotExistException.class)
    public ResponseEntity<?> handleRecordDoesNotFoundException(Exception e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                messageSource.getMessage(e.getMessage(), null, Locale.US));
    }
}
