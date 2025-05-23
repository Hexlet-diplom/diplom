package com.example.diplom.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom validation annotation to ensure password fields match.
 * Use on DTO classes where password confirmation is required,
 * e.g., during user registration.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
public @interface PasswordMatches {

    /**
     * The default error message when passwords do not match.
     *
     * @return the error message
     */
    String message() default "Passwords must match";

    /**
     * Used to specify validation groups.
     *
     * @return array of groups
     */
    Class<?>[] groups() default {};

    /**
     * Can be used to carry metadata information.
     *
     * @return array of payload classes
     */
    Class<? extends Payload>[] payload() default {};
}
