package com.minsang8332.workbenchapi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AppController {

    @GetMapping("/")
    public String index () {
        return "health check";
    }
}
