package com.sport.support.shared.common.annotations.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MobilePhoneNumberValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MobilePhoneNumber {
   String message() default "Invalid phone number";

   Class<?>[] groups() default {};

   Class<? extends Payload>[] payload() default {};
}
