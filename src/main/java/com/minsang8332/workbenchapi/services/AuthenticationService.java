package com.minsang8332.workbenchapi.services;

import com.minsang8332.workbenchapi.entities.User;
import com.minsang8332.workbenchapi.repositories.UserRepository;
import com.minsang8332.workbenchapi.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public final JwtUtil jwt;

    public void authenticate(String username, String password) {
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public User register (String username, String password) {
        User user = new User();
        user.setEmail(username);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }
}
