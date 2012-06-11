package com.mtt.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 */
@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
@Documented
public @interface UniqueEmail {

    String ERROR_MESSAGE = "The email address is already registered";

    /**
     * return error message
     */
    String message() default ERROR_MESSAGE;

    /**
     * return groups
     */
    Class<?>[] groups() default {
    };

    /**
     * return payload
     */
    Class<? extends Payload>[] payload() default {
    };
}
