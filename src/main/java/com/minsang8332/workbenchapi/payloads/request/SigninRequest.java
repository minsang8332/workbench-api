package com.minsang8332.workbenchapi.payloads.request;

import lombok.Data;

@Data
public class SigninRequest {
    private String email;
    private String password;
}
