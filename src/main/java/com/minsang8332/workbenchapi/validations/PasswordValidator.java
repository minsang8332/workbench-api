package com.minsang8332.workbenchapi.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        if (password == null || password.trim().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("패스워드는 필수입니다.")
                    .addConstraintViolation();
            return false;
        }

        // 패스워드 길이 처리
        if (password.length() < 8) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("패스워드는 최소 8자리 이상이어야 합니다.")
                    .addConstraintViolation();
            return false;
        }

        if (!password.matches("^(?=.*[a-zA-Z])(?=.*\\d).+$")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("패스워드는 영문 대소문자와 숫자를 포함해야 합니다.")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}