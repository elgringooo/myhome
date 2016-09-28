package com.myhome.customvalidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AfterTomorrowValidator.class)
@Documented
public @interface AfterTomorrow {
    String message() default "{AfterTomorrow.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

//Additionally, you can add the default AfterTomorrow.message message in ValidationMessages.properties