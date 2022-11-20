package com.teachmeskills.validator;

import com.teachmeskills.dto.UserDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidUser, UserDto> {
    @Override
    public boolean isValid(UserDto dto, ConstraintValidatorContext context) {
        if (dto.getPassword().equals(dto.getLogin())) {
            return true;
        }
        return false;
    }
}
