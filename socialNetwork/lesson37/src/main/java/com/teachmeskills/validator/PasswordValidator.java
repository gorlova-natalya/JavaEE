package com.teachmeskills.validator;

import com.teachmeskills.dto.AuthUserDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidUser, AuthUserDto> {
    @Override
    public boolean isValid(AuthUserDto dto, ConstraintValidatorContext context) {
        if (dto.getConfirmationPassword() == null) {
            return true;
        }
        return false;
    }
}
