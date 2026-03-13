package com.example.demo.scheduling;

import java.util.Objects;

public record Room(String id) {

    public Room {
        Objects.requireNonNull(id, "id is required");
        if (id.isBlank()) {
            throw new IllegalArgumentException("id cannot be blank");
        }
    }
}
