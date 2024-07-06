package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DemoController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/negative-hello")
    public String negativeHello() {
        return "bye bye";
    }
}
