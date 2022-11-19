package com.teachmeskills.validator;

import org.springframework.validation.annotation.Validated;

import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Validated(value = UserValidator.class)
public @interface ValidUser {
    String message () default "Custom message";
    Class<? extends Payload>[] payload () default {};
}
