package com.sport.support.infrastructure.configuration;

import com.sport.support.infrastructure.abstractions.adapters.web.AbstractController;
import com.sport.support.infrastructure.common.web.ErrorResponse;
import com.sport.support.infrastructure.common.web.Response;
import com.sport.support.infrastructure.exception.BusinessRuleException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ControllerExceptionHandler extends AbstractController {

   private final MessageSource messageSource;

   @ExceptionHandler(Exception.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   // TODO: 31.03.2022 without response entity it does not work
   public ResponseEntity<Response<ErrorResponse>> handleGeneralException(Exception e) {
      return ResponseEntity.badRequest().body(respond(new ErrorResponse("SSE", e.getMessage())));
   }

   @ExceptionHandler(BusinessRuleException.class)
   @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
   public ResponseEntity<Response<ErrorResponse>> handleBusinessRuleException(BusinessRuleException e) {
      String message = messageSource.getMessage(e.getMessage(), null, Locale.US);
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(respond(new ErrorResponse(e.getCode(), message)));
   }
}
