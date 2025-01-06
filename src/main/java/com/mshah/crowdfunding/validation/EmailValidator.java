package com.mshah.crowdfunding.validation;

import com.mshah.crowdfunding.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;


public class EmailValidator implements ConstraintValidator<EmailValidation, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(email)) {
            return false;
        }

        return !userService.isEmailPresent(email);
    }
}
