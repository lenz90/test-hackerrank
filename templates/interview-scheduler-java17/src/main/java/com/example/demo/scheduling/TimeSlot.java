package com.example.demo.scheduling;

import java.time.LocalTime;
import java.util.Objects;

public record TimeSlot(LocalTime startTime, LocalTime endTime) {

    public TimeSlot {
        Objects.requireNonNull(startTime, "startTime is required");
        Objects.requireNonNull(endTime, "endTime is required");
        if (!startTime.isBefore(endTime)) {
            throw new IllegalArgumentException("startTime must be before endTime");
        }
    }

    public boolean overlaps(TimeSlot other) {
        return startTime.isBefore(other.endTime()) && other.startTime().isBefore(endTime);
    }
}
