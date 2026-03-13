package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GreetingControllerTest {

    @Test
    void greetShouldReturnResponseFromService() {
        GreetingService greetingService = mock(GreetingService.class);
        when(greetingService.buildGreeting("Ana")).thenReturn("Hola, Ana!");

        GreetingController controller = new GreetingController(greetingService);
        GreetingResponse response = controller.greet("Ana");

        assertEquals("Hola, Ana!", response.getMessage());
        verify(greetingService).buildGreeting("Ana");
    }
}
