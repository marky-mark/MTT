package com.mtt.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = MatchesValidator.class)
@Documented
public @interface Matches {

    String message() default "{com.moa.podium.util.constraints.matches}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String field();

    String verifyField();

    /**
     * Defines several <code>@Match</code> annotations on the same element
     *
     * @see Matches
     */
    @Target({ TYPE, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Matches[] value();
    }

}
