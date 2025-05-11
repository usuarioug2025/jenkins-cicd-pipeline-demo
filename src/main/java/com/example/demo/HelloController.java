package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello, CI/CD with Jenkins!";
    }

    @GetMapping("/version")
    public String version() {
        return "v1.0.1";
    }
}