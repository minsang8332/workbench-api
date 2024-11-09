package com.minsang8332.workbenchapi.payloads.request;

import com.minsang8332.workbenchapi.validations.Password;
import com.minsang8332.workbenchapi.validations.Sku;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequest {

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "유효한 이메일 주소를 입력해주세요.")
    private String email;

    @Password
    private String password;

    @Sku
    private String sku;
}
