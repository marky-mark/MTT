package com.mtt.validation;

import com.mtt.domain.exception.UserNotFoundException;
import com.mtt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * UniqueValidator Constraint...Looks up BushFire & Legacy given an email Address
 */
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(UniqueEmail uniqueEmail) {
    }

    @Override
    public final boolean isValid(String userEmail, ConstraintValidatorContext constraintValidatorContext) {
        if (userEmail != null) {
            try {
                return userService.find(userEmail) == null;
            } catch (UserNotFoundException ex) {
               return true;
            }
        }
        return false;
    }
}
