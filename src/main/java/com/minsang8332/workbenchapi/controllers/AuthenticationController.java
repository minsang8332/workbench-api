package com.minsang8332.workbenchapi.controllers;

import com.minsang8332.workbenchapi.payloads.request.SigninRequest;
import com.minsang8332.workbenchapi.payloads.request.SignupRequest;
import com.minsang8332.workbenchapi.payloads.response.SigninResponse;
import com.minsang8332.workbenchapi.payloads.response.SignupResponse;
import com.minsang8332.workbenchapi.services.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody @Valid SignupRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        authenticationService.register(email, password);
        SignupResponse response = new SignupResponse();
        response.setResult(true);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<SigninResponse> signin(@RequestBody @Valid SigninRequest request) {
        SigninResponse response = new SigninResponse();
        authenticationService.authenticate(request.getEmail(), request.getPassword());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails user) {
            String accessToken = authenticationService.jwt.getAccessToken(user);
            String refreshToken = authenticationService.jwt.getRefreshToken(user);
            response.setResult(true);
            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + accessToken)
                    .header("Set-Cookie", "refresh-token=" + refreshToken + "; HttpOnly; Secure; SameSite=None")
                    .body(response);
        }
        return ResponseEntity.badRequest().body(response);
    }
}
