package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "DemoController", description = "Тестовый контроллер для проверки работы логирования")
public class DemoController {

    private final RestTemplate restTemplate;

    @GetMapping("/hello")
    @Operation(summary = "Возвращает строку с приветствием")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/bye")
    @Operation(summary = "Возвращает строку с прощанием")
    public String negativeHello() {
        return "bye bye";
    }

    @GetMapping("/outgoing")
    @Operation(summary = "Отправляет запрос по адресу https://cowsay.morecode.org/say и возваращает ответ")
    public String outgoingHello(@RequestParam String message) {
        return restTemplate.getForObject("https://cowsay.morecode.org/say?message={message}&format=text",
                String.class, message);
    }
}
