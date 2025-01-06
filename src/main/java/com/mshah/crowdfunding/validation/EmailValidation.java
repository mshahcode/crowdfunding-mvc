package com.mshah.crowdfunding.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailValidator.class)
@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailValidation {
    String message() default "Email is already registered!";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
