package com.sport.support.infrastructure.configuration;

import com.sport.support.infrastructure.exception.BusinessRuleException;
import com.sport.support.infrastructure.exception.DatabaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.Locale;

// TODO: ResponseEntity will be changed wrt application, error handling will be changed

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ControllerExceptionHandler {

   private final MessageSource messageSource;

   /*
   @ExceptionHandler(Exception.class)
   public ResponseEntity<?> handleGeneralException(Exception e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
   }*/

   @ExceptionHandler(EntityNotFoundException.class)
   public ResponseEntity<?> handleEntityNotFoundException(Exception e) {
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
          messageSource.getMessage(e.getMessage(), null, Locale.US));
   }

   @ExceptionHandler(BusinessRuleException.class)
   public ResponseEntity<?> handleBusinessRuleException(Exception e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
          messageSource.getMessage(e.getMessage(), null, Locale.US));
   }

   @ExceptionHandler(DatabaseException.class)
   public ResponseEntity<?> handleDatabaseException(Exception e) {
      log.error("DatabaseException ", e);
      return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Service is unavailable");
   }
}
