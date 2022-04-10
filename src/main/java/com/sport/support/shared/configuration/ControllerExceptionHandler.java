package com.sport.support.shared.configuration;

import com.sport.support.shared.abstractions.adapters.web.AbstractController;
import com.sport.support.shared.common.web.ErrorResponse;
import com.sport.support.shared.common.web.Response;
import com.sport.support.shared.exception.BusinessRuleException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ControllerExceptionHandler extends AbstractController {

   private final MessageSource messageSource;

   @ExceptionHandler(Exception.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public Response<ErrorResponse> handleGeneralException(Exception e) {
      return respond(new ErrorResponse("SSE", e.getMessage()));
   }

   @ExceptionHandler(BusinessRuleException.class)
   @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
   public Response<ErrorResponse> handleBusinessRuleException(BusinessRuleException e, Locale locale) {
      var message = messageSource.getMessage(e.getMessage(), null, locale);
      return respond(new ErrorResponse(e.getCode(), message));
   }
}
