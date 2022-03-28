package com.sport.support.infrastructure.common.annotations.stereotype;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Transactional
public @interface UseCase {

   @AliasFor(annotation = Component.class)
   String value() default "";

}