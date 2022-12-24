package com.teachmeskills.validator;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Objects;

@ValidUser
public class UserValidator {

    public boolean validate(BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            if (bindingResult.hasFieldErrors("login")) {
                model.addAttribute("loginErrorText",
                        Objects.requireNonNull(bindingResult.getFieldError("login")).getDefaultMessage());
            }
            if (bindingResult.hasFieldErrors("password")) {
                model.addAttribute("passwordErrorText",
                        Objects.requireNonNull(bindingResult.getFieldError("password")).getDefaultMessage());
            }
            return false;
        }
        return true;
    }
}
