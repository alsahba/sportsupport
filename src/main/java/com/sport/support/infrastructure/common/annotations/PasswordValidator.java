package com.sport.support.infrastructure.common.annotations;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

   private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

   @Override
   public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
      return StringUtils.hasLength(s) && s.matches(PASSWORD_PATTERN);
   }

   @Override
   public void initialize(Password constraintAnnotation) {
      ConstraintValidator.super.initialize(constraintAnnotation);
   }
}
