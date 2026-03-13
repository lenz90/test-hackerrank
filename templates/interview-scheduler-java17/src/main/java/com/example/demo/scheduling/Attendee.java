package com.example.demo.scheduling;

import java.util.Objects;

public record Attendee(String id, String name) {

    public Attendee {
        Objects.requireNonNull(id, "id is required");
        Objects.requireNonNull(name, "name is required");
        if (id.isBlank()) {
            throw new IllegalArgumentException("id cannot be blank");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("name cannot be blank");
        }
    }
}
