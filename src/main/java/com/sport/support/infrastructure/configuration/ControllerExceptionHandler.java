package com.sport.support.infrastructure.configuration;

import com.sport.support.infrastructure.exception.BusinessRuleException;
import com.sport.support.infrastructure.exception.RecordIsNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

// TODO: ResponseEntity will be changed wrt application, error handling will be changed

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {

   private final MessageSource messageSource;

   @ExceptionHandler(Exception.class)
   public ResponseEntity<?> handleGeneralException(Exception e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
   }

   @ExceptionHandler(RecordIsNotFoundException.class)
   public ResponseEntity<?> handleRecordDoesNotFoundException(Exception e) {
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
          messageSource.getMessage(e.getMessage(), null, Locale.US));
   }

   @ExceptionHandler(BusinessRuleException.class)
   public ResponseEntity<?> handleBusinessRuleException(Exception e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
          messageSource.getMessage(e.getMessage(), null, Locale.US));
   }
}
