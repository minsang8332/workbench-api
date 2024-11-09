package com.minsang8332.workbenchapi.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class SkuValidator implements ConstraintValidator<Sku, String> {
    @Override
    public boolean isValid(String sku, ConstraintValidatorContext context) {

        // Base64 인코딩 값 체크
        if (StringUtils.hasText(sku) && sku.matches("^[A-Za-z0-9+/=]+$")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("제품 키는 Base64 인코딩된 값이어야 합니다.")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
