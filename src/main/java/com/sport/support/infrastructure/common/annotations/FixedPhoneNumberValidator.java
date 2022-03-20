package com.sport.support.infrastructure.common.annotations;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FixedPhoneNumberValidator implements ConstraintValidator<FixedPhoneNumber, String> {

   private static final String PHONE_NUMBER_REGEX = "^[1-9][0-9]{6}$";

   @Override
   public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
      return StringUtils.hasLength(s) && s.matches(PHONE_NUMBER_REGEX);
   }

   @Override
   public void initialize(FixedPhoneNumber constraintAnnotation) {
      ConstraintValidator.super.initialize(constraintAnnotation);
   }
}
