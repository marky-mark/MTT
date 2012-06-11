package com.mtt.validation;

import org.mvel2.MVEL;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * TODO: Copied from Stackoverflow.com - NEEDS UNIT TESTS
 */
public class MatchesValidator implements ConstraintValidator<Matches, Object> {

    private String field;
    private String verifyField;
    private String message;

    public final void initialize(Matches constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.verifyField = constraintAnnotation.verifyField();
        this.message = constraintAnnotation.message();
    }

    public final boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldObj = MVEL.getProperty(field, value);
        Object verifyFieldObj = MVEL.getProperty(verifyField, value);

        boolean neitherSet = (fieldObj == null) && (verifyFieldObj == null);

        if (neitherSet) {
            return true;
        }

        boolean matches = (fieldObj != null) && fieldObj.equals(verifyFieldObj);

        if (!matches) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addNode(verifyField)
                    .addConstraintViolation();
        }

        return matches;
    }
}
