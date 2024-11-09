package com.minsang8332.workbenchapi.controllers;

import com.minsang8332.workbenchapi.payloads.request.SigninRequest;
import com.minsang8332.workbenchapi.payloads.request.SignupRequest;
import com.minsang8332.workbenchapi.payloads.response.SigninResponse;
import com.minsang8332.workbenchapi.payloads.response.SignupResponse;
import com.minsang8332.workbenchapi.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest request) {
        authenticationService.register(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new SignupResponse());
    }
    @PostMapping("/signin")
    public ResponseEntity<SigninResponse> signin(@RequestBody SigninRequest request) {
        Authentication authentication = authenticationService.authenticate(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new SigninResponse());
    }
}
