package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GreetingServiceTest {

    private final GreetingService service = new GreetingService();

    @Test
    void buildGreetingShouldUseDefaultName() {
        assertEquals("Hola, World!", service.buildGreeting(""));
    }

    @Test
    void buildGreetingShouldUseProvidedName() {
        assertEquals("Hola, Luisa!", service.buildGreeting("Luisa"));
    }
}
