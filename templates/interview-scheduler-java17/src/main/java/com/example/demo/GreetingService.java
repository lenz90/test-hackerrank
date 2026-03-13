package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String buildGreeting(String name) {
        String safeName = (name == null || name.isBlank()) ? "World" : name;
        return "Hola, " + safeName + "!";
    }
}
